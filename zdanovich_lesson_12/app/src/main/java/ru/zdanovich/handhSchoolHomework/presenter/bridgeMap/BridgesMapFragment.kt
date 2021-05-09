package ru.zdanovich.handhSchoolHomework.presenter.bridgeMap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgesMapBinding
import ru.zdanovich.handhSchoolHomework.databinding.ViewHolderBridgeBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.*
import ru.zdanovich.handhSchoolHomework.presenter.helpers.onBind

class BridgesMapFragment : androidx.fragment.app.Fragment(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    private var _binding: FragmentBridgesMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BridgesListViewModel by viewModels { BridgesListViewModelFactory() }
    private var mMap: GoogleMap? = null
    private lateinit var bridgesMap: Map<Int, Bridge>

    private var isRationaleShown = false
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @SuppressLint("MissingPermission")
    override fun onAttach(context: Context) {
        super.onAttach(context)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onLocationPermissionGranted()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBridgesMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fbm_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.state.observe(this.viewLifecycleOwner, this::setState)
        if (viewModel.state.value is BridgeListState.Default) viewModel.getBridges()
    }

    private fun setupToolbar() {
        binding.fbmToolbar.apply {
            inflateMenu(R.menu.fragment_bridges_map_menu)

            setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_fbm_refresh -> refresh()
                    R.id.action_on_location -> onLocation()
                    else -> return@setOnMenuItemClickListener false
                }

                return@setOnMenuItemClickListener true
            }
        }
    }

    private fun refresh() {
        viewModel.getBridges()
    }

    private fun setState(bridgeListState: BridgeListState) =
        when (bridgeListState) {
            is BridgeListState.Default,
            is BridgeListState.Loading -> {
                setLoading(true)
            }
            is BridgeListState.Error.Internet -> {
                setLoading(false)
                showToast(getString(R.string.error_internet_message))
            }
            is BridgeListState.Error.Other -> {
                setLoading(false)
                showToast(getString(R.string.other_error_message))
            }
            is BridgeListState.Success -> {
                bridgesMap = bridgeListState.bridges
                setBridgesOnMap()
                setLoading(false)
            }
        }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun setLoading(loading: Boolean) {
        binding.fbmLoader.isVisible = loading
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val state = viewModel.state.value
        if (state is BridgeListState.Success) {
            bridgesMap = state.bridges
            setBridgesOnMap()
        }
        
        googleMap.setOnMarkerClickListener(this)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(spbRegion, 0))
    }

    private fun setBridgesOnMap() {
        for (bridge in bridgesMap.values) {
            val bridgeCoordinate =
                LatLng(bridge.coordinate.latitude, bridge.coordinate.longitude)
            val marker = mMap?.addMarker(
                MarkerOptions()
                    .position(bridgeCoordinate)
                    .icon(getBridgeIconBitmap(bridge.getBridgeStatus()))
            )

            marker?.tag = bridge.id
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val id: Int = marker.tag as Int
        bridgesMap[id]?.let { bridge ->
            val snackBar = Snackbar.make(binding.root, EMPTY, Snackbar.LENGTH_LONG)
            val snackBinding = ViewHolderBridgeBinding.inflate(layoutInflater)
            snackBinding.onBind(bridge)
            val snackBarLayout = snackBar.view as SnackbarLayout
            snackBar.view.setBackgroundColor(Color.WHITE)

            snackBar.view.setOnClickListener {
                snackBar.dismiss()
                val action = BridgesMapFragmentDirections.actionBridgesMapFragmentToBridgeInfoFragment(bridge.id)
                findNavController().navigate(action)
            }

            snackBarLayout.addView(snackBinding.root)
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            snackBar.show()
        }

        return true
    }

    private fun getBridgeIconBitmap(status: Bridge.BridgeStatus): BitmapDescriptor =
        when (status) {
            Bridge.BridgeStatus.Close -> bridgeBitmapCloseMarker
            Bridge.BridgeStatus.Open -> bridgeBitmapOpenMarker
            Bridge.BridgeStatus.SoonClose -> bridgeBitmapSoonMarker
        }

    private fun onLocation() {
        activity?.let {
            when {
                ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED -> onLocationPermissionGranted()
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ->
                    showLocationPermissionExplanationDialog()
                isRationaleShown -> showLocationPermissionDeniedDialog()
                else -> requestLocationPermission()
            }
        }
    }

    private fun requestLocationPermission() {
        context?.let {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    private fun onLocationPermissionGranted() {
        context?.let {
            mMap?.isMyLocationEnabled = true
        }
    }

    private fun showLocationPermissionExplanationDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(R.string.permission_dialog_explanation_text)
                .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                    isRationaleShown = true
                    requestLocationPermission()
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun showLocationPermissionDeniedDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(R.string.permission_dialog_denied_text)
                .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                    startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + it.packageName)
                        )
                    )
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMap?.setOnMapClickListener(null)
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        requestPermissionLauncher.unregister()
    }

    companion object {
        const val EMPTY = ""
        val spbRegion by lazy { LatLngBounds(LatLng(59.51, 29.58), LatLng(60.18, 30.97)) }

        val bridgeBitmapCloseMarker: BitmapDescriptor by lazy {
            BitmapDescriptorFactory.fromResource(
                R.drawable.ic_brige_late
            )
        }

        val bridgeBitmapSoonMarker: BitmapDescriptor by lazy {
            BitmapDescriptorFactory.fromResource(
                R.drawable.ic_brige_soon
            )
        }

        val bridgeBitmapOpenMarker: BitmapDescriptor by lazy {
            BitmapDescriptorFactory.fromResource(
                R.drawable.ic_brige_normal
            )
        }
    }
}
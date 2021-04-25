package ru.zdanovich.handhSchoolHomework.presenter.bridgeMap

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.FragmentBridgesMapBinding
import ru.zdanovich.handhSchoolHomework.databinding.ViewHolderBridgeBinding
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgeAdapter
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgeListState
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListViewModel
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListViewModelFactory
import ru.zdanovich.handhSchoolHomework.presenter.helpers.getBridgeIcon
import ru.zdanovich.handhSchoolHomework.presenter.helpers.getBridgeIconBitmap

class BridgesMapFragment : androidx.fragment.app.Fragment(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    private var _binding: FragmentBridgesMapBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BridgesListViewModel by viewModels { BridgesListViewModelFactory() }

    private lateinit var mMap: GoogleMap

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

        //viewModel.state.observe(this.viewLifecycleOwner, this::setState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fbm_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (viewModel.state.value is BridgeListState.Default) viewModel.getBridges()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (viewModel.state.value is BridgeListState.Success) {
            val bridgeList = (viewModel.state.value as BridgeListState.Success).bridges

            for (bridge in bridgeList) {
                val bridgeCoordinate =
                    LatLng(bridge.coordinate.latitude, bridge.coordinate.longitude)
                val marker = mMap.addMarker(
                    MarkerOptions()
                        .position(bridgeCoordinate)
                        .icon(BitmapDescriptorFactory.fromResource(getBridgeIconBitmap(bridge.getBridgeStatus())))
                )

                marker.tag = bridge.id
            }

            mMap.setOnMarkerClickListener(this)
        }

        val spbCenter = LatLng(59.57, 30.19)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spbCenter))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val id: Int = marker.tag as Int
        if (viewModel.state.value is BridgeListState.Success) {
            val bridgeList = (viewModel.state.value as BridgeListState.Success).bridges
            val bridge = bridgeList[id - 1]
            val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)

            val snackBinding = ViewHolderBridgeBinding.inflate(layoutInflater)
            snackBinding.vhbStatusIcon.setImageResource(getBridgeIcon(bridge.getBridgeStatus()))
            snackBinding.vhbBridgeTimeDivorces.text =
                bridge.bridgeDivorcesTimes.joinToString(postfix = BridgeAdapter.BRIDGE_DIVORCES_TIME_POSTFIX) { it.toUiString() }
            snackBinding.vhbBridgeName.text = bridge.name

            val snackbarLayout = snackbar.view as SnackbarLayout
            snackbar.view.setBackgroundColor(Color.WHITE)
            snackbarLayout.setPadding(0, 0, 0, 0)
            snackbarLayout.addView(snackBinding.root)
            snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE


            snackbar.show()
        }
        return true
    }
}
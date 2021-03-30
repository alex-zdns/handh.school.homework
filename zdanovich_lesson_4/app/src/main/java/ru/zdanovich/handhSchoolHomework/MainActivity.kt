package ru.zdanovich.handhSchoolHomework

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InfoItemAdapter.OnRecyclerItemClicked {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val repository = InfoItemRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolBar()
        setupRecycleView()
    }

    private fun setupRecycleView() {
        binding.maRecyclerView.let {
            val spanCount = resources.getInteger(R.integer.span_count)
            val adapter = InfoItemAdapter(repository.getInfoItem(), spanCount, this)
            val layoutManager = GridLayoutManager(this, spanCount)

            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapter.getItemViewType(position)) {
                        InfoItemAdapter.ITEM_LONG -> spanCount
                        InfoItemAdapter.ITEM_SHORT -> 1
                        else -> throw IllegalArgumentException()
                    }
                }
            }

            it.adapter = adapter
            it.layoutManager = layoutManager

            val offset = resources.getDimensionPixelOffset(R.dimen.info_item_margin)
            it.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = offset
                    outRect.left = offset
                    outRect.right = offset
                    outRect.bottom = offset
                }
            })
        }
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.maToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> showHomeToast()
            R.id.action_info -> showInfoDialog()
            else -> return false
        }

        return true
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.ma_info_dialog_title))
            .setMessage(getString(R.string.ma_info_dialog_message))
            .setIcon(R.drawable.ic_info)
            .setPositiveButton(getString(R.string.ma_info_dialog_positive_button)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    private fun showHomeToast() {
        Toast.makeText(this, getString(R.string.ma_home_button_message), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(title: String) = showItemSnackBar(title)

    private fun showItemSnackBar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}
package com.deanezra.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.deanezra.R
import com.deanezra.network.NetworkStatus
import com.deanezra.network.model.BasePlants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_plant_data.*
import javax.inject.Inject

class PlantDataActivity : AppCompatActivity() {

    @Inject
    lateinit var networkDataActivityViewModel: PlantDataActivityViewModel

    @Inject
    lateinit var networkDataAdapter: PlantDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_data)
        prepareView()
        callApiAndUpdateUI()
    }

    private fun prepareView() {
        plantsRecycleView.itemAnimator = DefaultItemAnimator()
        plantsRecycleView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        this.plantsRecycleView.adapter = networkDataAdapter
        networkDataAdapter.onItemClick = { user ->

            // do something with your item

           val bottomSheetFragment =
                PlantDataDetailFragment(user)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }

    private fun callApiAndUpdateUI() {
        networkDataProgressBar.visibility = View.VISIBLE
        networkDataActivityViewModel.networkLiveData.observe(this,
            Observer<BasePlants> { baseDataStore ->
                networkDataProgressBar.visibility = View.GONE

                if (baseDataStore != null) {
                    networkDataAdapter.addItemList(baseDataStore.plantlist!!)
                }
                else{
                    networkDataActivityViewModel.statusLiveData.value = NetworkStatus.FAIL
                }
            })


        // status of actions
        networkDataActivityViewModel.statusLiveData.observe(this,
            Observer<NetworkStatus> { status ->
                networkDataProgressBar.visibility = View.GONE
                onNetWorkStateChanged(status)
            })
        networkDataActivityViewModel.fetchPlants()
    }

    private fun onNetWorkStateChanged(state: NetworkStatus) = when (state) {
        NetworkStatus.INTERNET_CONNECTION -> showSnackBar(getString(R.string.msg_no_internet_network))
        NetworkStatus.SERVER_ERROR -> showSnackBar(getString(R.string.msg_server_error))
        NetworkStatus.FAIL -> showSnackBar(getString(R.string.msg_something_went_wrong))
        NetworkStatus.NO_RECORDS -> showSnackBar(getString(R.string.msg_no_records))

        else -> showSnackBar(getString(R.string.msg_unknown))
    }
    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
            .show()
    }

}
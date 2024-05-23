package com.cr.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cr.coroutinesdemo.databinding.ActivityStateFlowBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowActivity : AppCompatActivity() {

    private val TAG = "StateFlowActivity"
    lateinit var activityStateFlowBinding: ActivityStateFlowBinding
    lateinit var stateFlowViewModel: StateFlowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityStateFlowBinding = ActivityStateFlowBinding.inflate(layoutInflater)
        setContentView(activityStateFlowBinding.root)

        stateFlowViewModel = ViewModelProvider(this).get(StateFlowViewModel::class.java)
        activityStateFlowBinding.btnSave.setOnClickListener {
            stateFlowViewModel.buildUp(activityStateFlowBinding.etInput.text.toString().trim())
        }

        lifecycleScope.launch{
            stateFlowViewModel.uiState.collect(){
                activityStateFlowBinding.tvShow.text = it

                Log.d(TAG, "stateFlowViewModel.uiState.collect ")
            }
        }
    }
}
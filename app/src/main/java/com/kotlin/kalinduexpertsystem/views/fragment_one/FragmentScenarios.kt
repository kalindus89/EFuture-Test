package com.kotlin.kalinduexpertsystem.views.fragment_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.kalinduexpertsystem.ExpertSystemApi
import com.kotlin.kalinduexpertsystem.R
import com.kotlin.kalinduexpertsystem.RecyclerScenarioClickListener
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse
import com.kotlin.kalinduexpertsystem.repository.SystemRepository
import com.kotlin.kalinduexpertsystem.viewmodel.SystemViewModel
import com.kotlin.kalinduexpertsystem.viewmodel.SystemViewModelFactory


class FragmentScenarios : Fragment(), RecyclerScenarioClickListener {


    private lateinit var factory: SystemViewModelFactory
    private lateinit var viewModel: SystemViewModel
    private lateinit var recyclerViewScenario: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_scenarios, container, false)

        recyclerViewScenario=view.findViewById<RecyclerView>(R.id.recyclerViewScenario)

        val api = ExpertSystemApi()
        val repository = SystemRepository(api)
        factory= SystemViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(SystemViewModel::class.java)
        viewModel.getScenarios()
        viewModel.scenariosLive.observe(viewLifecycleOwner, Observer { scenarios->
            recyclerViewScenario.also {
                it.layoutManager= LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= ScenarioRecyclerAdapter(scenarios,this)

            }
        })

        return view
    }

    override fun onRecyclerScenarioItemClick(view: View, scenario: ModelScenariosResponse) {

        val action = FragmentScenariosDirections.actionFragmentScenariosToFragmentCases(scenario.caseid) // passing values to 2nd fragments. sync safegard in both gradles
        Navigation.findNavController( view).navigate(action)

    }


}
package com.kotlin.kalinduexpertsystem.views.fragment_one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.kalinduexpertsystem.R
import com.kotlin.kalinduexpertsystem.RecyclerScenarioClickListener
import com.kotlin.kalinduexpertsystem.databinding.RecyclerviewItemScenarioBinding
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse

class ScenarioRecyclerAdapter(private val scenario: List<ModelScenariosResponse>, private val listener: RecyclerScenarioClickListener) :
    RecyclerView.Adapter<ScenarioRecyclerAdapter.ScenarioViewHolder>() {


    inner class ScenarioViewHolder(val recyclerviewItemScenarioBinding: RecyclerviewItemScenarioBinding):
        RecyclerView.ViewHolder(recyclerviewItemScenarioBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScenarioViewHolder(

        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_item_scenario,
            parent, false
        )
    )

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {

        holder.recyclerviewItemScenarioBinding.modelScenariosResponse=scenario[position]

        holder.recyclerviewItemScenarioBinding.selectCase.setOnClickListener{
            listener.onRecyclerScenarioItemClick(holder.recyclerviewItemScenarioBinding.selectCase,scenario[position])
        }


    }

    override fun getItemCount(): Int {
        return scenario.size
    }
}
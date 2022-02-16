package com.kotlin.kalinduexpertsystem.views.fragment_second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.kalinduexpertsystem.R
import com.kotlin.kalinduexpertsystem.RecyclerCaseClickListener
import com.kotlin.kalinduexpertsystem.RecyclerScenarioClickListener
import com.kotlin.kalinduexpertsystem.databinding.RecyclerviewItemCasesBinding
import com.kotlin.kalinduexpertsystem.databinding.RecyclerviewItemScenarioBinding
import com.kotlin.kalinduexpertsystem.models.ModelAnswers
import com.kotlin.kalinduexpertsystem.models.ModelScenarioCaseResponse
import com.kotlin.kalinduexpertsystem.models.ModelScenariosResponse

class CasesRecyclerAdapter(private val cases: List<ModelAnswers>, private val listener: RecyclerCaseClickListener) :
    RecyclerView.Adapter<CasesRecyclerAdapter.CaseViewHolder>() {


    inner class CaseViewHolder(val recyclerviewItemCasesBinding: RecyclerviewItemCasesBinding):
        RecyclerView.ViewHolder(recyclerviewItemCasesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CaseViewHolder(

        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_item_cases,
            parent, false
        )
    )

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {

        holder.recyclerviewItemCasesBinding.modelAnswers=cases[position]
        holder.recyclerviewItemCasesBinding.answer.setOnClickListener{
            listener.onRecyclerCaseItemClick(holder.recyclerviewItemCasesBinding.answer,cases[position])
        }


    }

    override fun getItemCount(): Int {
        return cases.size
    }
}
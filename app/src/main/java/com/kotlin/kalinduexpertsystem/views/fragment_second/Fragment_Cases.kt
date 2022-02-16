package com.kotlin.kalinduexpertsystem.views.fragment_second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.kalinduexpertsystem.ExpertSystemApi
import com.kotlin.kalinduexpertsystem.R
import com.kotlin.kalinduexpertsystem.RecyclerCaseClickListener
import com.kotlin.kalinduexpertsystem.models.ModelAnswers
import com.kotlin.kalinduexpertsystem.repository.SystemRepository
import com.kotlin.kalinduexpertsystem.viewmodel.SystemViewModel
import com.kotlin.kalinduexpertsystem.viewmodel.SystemViewModelFactory
import com.kotlin.kalinduexpertsystem.views.fragment_one.ScenarioRecyclerAdapter

class Fragment_Cases : Fragment() , RecyclerCaseClickListener{

    val getPassedArgumentValues : Fragment_CasesArgs by navArgs()

    private lateinit var factory: SystemViewModelFactory
    private lateinit var viewModel: SystemViewModel
    private lateinit var recyclerViewCase: RecyclerView
    private lateinit var  repository: SystemRepository
    private lateinit var  textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment__cases, container, false)

        recyclerViewCase=view.findViewById<RecyclerView>(R.id.recyclerViewCases)

        val imageView =view.findViewById<ImageView>(R.id.imageView)
         textView =view.findViewById<TextView>(R.id.textView)

        val api = ExpertSystemApi()
        repository = SystemRepository(api)
        factory= SystemViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(SystemViewModel::class.java)
        viewModel.getCases(getPassedArgumentValues.caseid)
        viewModel.casesLive.observe(viewLifecycleOwner, Observer { cases->
         //   Toast.makeText(requireContext(),cases[0].text, Toast.LENGTH_SHORT).show()
            recyclerViewCase.also {
                it.layoutManager= LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= CasesRecyclerAdapter(cases[0].answers,this)

            }
            if(!cases[0].image.isNullOrEmpty()) {
                Glide.with(view).load(cases[0].image).into(imageView)
            }
            if(!cases[0].text.isNullOrEmpty()) {
                textView.text=cases[0].text
            }


        })

        // Toast.makeText(requireContext(),getPassedArgumentValues.caseid.toString()+" ", Toast.LENGTH_SHORT).show()


        return view
    }

    override fun onRecyclerCaseItemClick(view: View, answer: ModelAnswers) {

        Toast.makeText(requireContext(),answer.text+" "+answer.caseid.toString(), Toast.LENGTH_SHORT).show()
        viewModel.getCases(answer.caseid)
        // repository = SystemRepository(api,getPassedArgumentValues.caseid)
/*
        var repository2 :SystemRepository= SystemRepository(ExpertSystemApi(),answer.caseid)

         lateinit var factory2: SystemViewModelFactory
         lateinit var viewModel2: SystemViewModel

        factory2= SystemViewModelFactory(repository2)
        viewModel2 = ViewModelProvider(this,factory2).get(SystemViewModel::class.java)
        viewModel2.postCases()
        viewModel2.casesLive.observe(viewLifecycleOwner, Observer { cases->
               Toast.makeText(requireContext(),cases[0].text+" "+cases[0].id.toString(), Toast.LENGTH_SHORT).show()
            recyclerViewCase.also {
                it.layoutManager= LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter= CasesRecyclerAdapter(cases[0].answers,this)

            }

            if(!cases[0].text.isNullOrEmpty()) {
                textView.text=cases[0].text
            }


        })*/
    }


}
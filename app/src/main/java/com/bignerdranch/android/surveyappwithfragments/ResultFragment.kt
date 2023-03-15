package com.bignerdranch.android.surveyappwithfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    lateinit var numberOfYeses: TextView
    lateinit var numberOfNos: TextView
    lateinit var resetButton: Button
    lateinit var continueButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SurveyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        numberOfYeses = view.findViewById(R.id.number_of_yes_text_view)
        numberOfNos = view.findViewById(R.id.number_of_no_text_view)
        resetButton = view.findViewById(R.id.reset_button)
        continueButton = view.findViewById(R.id.continue_button)

        numberOfYeses.text = surveyViewModel.yesCount.toString()
        numberOfNos.text = surveyViewModel.noCount.toString()

        resetButton.setOnClickListener {
            surveyViewModel.resetCount()
            numberOfYeses.text = surveyViewModel.yesCount.toString()
            numberOfNos.text = surveyViewModel.noCount.toString()
        }

        continueButton.setOnClickListener {}

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}

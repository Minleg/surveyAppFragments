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
 * Use the [SurveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val RESULT_SENT = "com.bignerdranch.android.surveyappwithfragments.Result_Sent" // Key used to notify parent activity
class SurveyFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var yesOptionButton: Button
    private lateinit var noOptionButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SurveyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_survey, container, false)
        questionTextView = view.findViewById(R.id.question_text_view)
        yesOptionButton = view.findViewById(R.id.yes_option_button)
        noOptionButton = view.findViewById(R.id.no_option_button)

        val question = surveyViewModel.getCurrentQuestion()
        questionTextView.setText(question)

        questionTextView.setOnClickListener {
            val nextQuestion = surveyViewModel.getNextQuestion() // sets the question to the next question
            questionTextView.setText(nextQuestion)
        }

        yesOptionButton.setOnClickListener {
            surveyViewModel.updateYesCount()
            updateCount()
        }

        noOptionButton.setOnClickListener {
            surveyViewModel.updateNoCount()
            updateCount()
        }
        return view
    }

    private fun updateCount() {
        // This function notifies parent activity of the user either clicking on yes or no button
        parentFragmentManager.setFragmentResult(RESULT_SENT, Bundle.EMPTY) // Bundle.EMPTY because we don't need to send any data
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment SurveyFragment.
         */
        @JvmStatic
        fun newInstance() = SurveyFragment()
    }
}

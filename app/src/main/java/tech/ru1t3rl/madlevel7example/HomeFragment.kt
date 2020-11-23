package tech.ru1t3rl.madlevel7example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import tech.ru1t3rl.madlevel7example.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        viewModel.getQuiz()

        binding.btCreateQuiz.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_createQuizFragment2)
        }

        viewModel.quiz.observe(viewLifecycleOwner, {
            if(!it.question.isBlank() && !it.answer.isBlank()) {
                binding.btStartQuiz.alpha = 1.0f
                binding.btStartQuiz.isClickable = true

                binding.btStartQuiz.setOnClickListener {
                    navController.navigate(R.id.action_homeFragment_to_quizFragment)
                }
            }
        })
    }
}
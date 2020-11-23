package tech.ru1t3rl.madlevel7example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.ru1t3rl.madlevel7example.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerQuiz()
    }

    private fun observerQuiz() {
        viewModel.quiz.observe(viewLifecycleOwner, {
            val quiz = it
            binding.tvQuizQuestion.text = quiz.question

            binding.btConfirmAnswer.setOnClickListener {
                if (viewModel.isAnwerCorrect(binding.etQuizAnswer.text.toString())) {
                    Toast.makeText(context, "Your answer is correct!", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "You answer is incorrect! The correct answer was: ${quiz.answer}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}
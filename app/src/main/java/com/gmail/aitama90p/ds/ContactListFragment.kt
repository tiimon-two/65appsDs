package com.gmail.aitama90p.ds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gmail.aitama90p.ds.databinding.ContactListBinding

class ContactListFragment : Fragment() {
    private var _binding: ContactListBinding? = null
    private val binding get() = _binding!!
    private val dataModel: DataModel by activityViewModels()
    private val TEST_ID = "13434945"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = requireActivity().getString(R.string.contact_list_fragment_name)

        binding.contactItem.setOnClickListener(){
            parentFragmentManager.beginTransaction()
                .replace(R.id.contacts_container, ContactDetailsFragment.newInstance())
                .addToBackStack(null)
                .commit()

            dataModel.idMessage.value = TEST_ID
            dataModel.nameMessage.value = binding.contactName.text.toString()
            dataModel.phoneMessage.value = binding.contactNumber.text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }
}
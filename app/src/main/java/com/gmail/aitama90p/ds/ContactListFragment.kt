package com.gmail.aitama90p.ds

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.aitama90p.ds.databinding.ContactListBinding
import java.lang.ref.WeakReference


class ContactListFragment : Fragment() {
    private var _binding: ContactListBinding? = null
    private val binding get() = _binding!!
    private var contactListService: ContactListService.ContactListServiceInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ContactListService.ContactListServiceInterface) {
            contactListService = context
        }
    }

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
        contactListService?.getService()?.showContactList(WeakReference(this))

        binding.contactView.contactItem.setOnClickListener(){
            parentFragmentManager.beginTransaction()
                .replace(R.id.contacts_container, ContactDetailsFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        contactListService = null
        super.onDetach()
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }

    fun setData(data: List<Contact>) {
        binding.contactView.apply {
            var contactData = data.first()
            contactName.text = contactData.name
            contactNumber.text = contactData.phone
            contactImage.setImageResource(contactData.photo)
        }
    }
}
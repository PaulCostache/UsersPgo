package com.example.userspgo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.userspgo.Model.PostModelItem
import com.example.userspgo.Model.UserModelItem
import com.example.userspgo.ViewModel.MainActivityViewModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private lateinit var userModel: UserModelItem
    private lateinit var userDetailsViewModel: MainActivityViewModel

    companion object {
        const val ARG_USER_MODEL = "userModel"

        fun newInstance(userModel: UserModelItem,): UserDetailsFragment {
            val args = Bundle()
            args.putParcelable(ARG_USER_MODEL, userModel)
            val fragment = UserDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userModel = it.getParcelable(ARG_USER_MODEL)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.FragmentTitle)
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_user_details, container, false)
        val txtUserName = view.findViewById<TextView>(R.id.txttest)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val txtViewPostTitle = view.findViewById<TextView>(R.id.txtViewPostTitle)
        val txtViewPost = view.findViewById<TextView>(R.id.txtViewPost)
        val imgUserAvatar = view.findViewById<CircleImageView>(R.id.fragmentImageView)

        txtUserName.text = userModel.name
        txtEmail.text = userModel.email

        userModel.imageUrl?.let { imageUrl ->
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgUserAvatar)
        }

        userDetailsViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        userDetailsViewModel.getUserPosts(userModel.id ?: 0)
            .observe(viewLifecycleOwner) { userPost ->
                userPost?.let {
                    userPost.firstOrNull()?.body?.let { body ->
                        Log.e("UserDetailFragment", "UserPost: $body")
                        txtViewPostTitle.text=userPost.get(0).title
                        txtViewPost.text=body
                    }
                }?: run {
                    // handle null case
                    txtViewPostTitle.text=getString(R.string.empty_title)
                    txtViewPost.text=getString(R.string.no_post_provided)
                }
            }

        return view
    }

}

package com.responsi.pcs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.responsi.pcs.LoginActivity.Companion.sessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)


        val btnLogout = view.findViewById(R.id.btnLogout) as Button
        btnLogout.setOnClickListener{
            val dialog = AlertDialog.Builder(requireActivity())
            dialog.apply {
                setTitle("Konfirmasi Keluar Akun")
                setMessage("Yakin ingin keluar akun?")
                setNegativeButton("Batal") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                setPositiveButton("Keluar") { dialogInterface, i ->
                    CoroutineScope(Dispatchers.IO).launch {
                        sessionManager.clearSession()
                        val moveIntent = Intent(activity, LoginActivity::class.java)
                        startActivity(moveIntent)
                        activity?.finish()
                        dialogInterface.dismiss()
                    }
                }
            }
            dialog.show()
        }

//        findNavController().navigate(R.id.settingFragment)
        return view
    }

}
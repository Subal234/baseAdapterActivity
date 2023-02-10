package com.androidstudio.baseadapteractivity

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.androidstudio.baseadapteractivity.databinding.ActivityMainBinding
import com.androidstudio.baseadapteractivity.databinding.EditBtnDialogeBinding
import com.androidstudio.baseadapteractivity.databinding.FabBtnDialogBinding

class MainActivity : AppCompatActivity(),ClickInterface {

    lateinit var binding:ActivityMainBinding
    lateinit var listAdapter: ListAdapter
    var userArray = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = ListAdapter(userArray,this)
        binding.lvListView.adapter = listAdapter



        binding.fabBtn.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = FabBtnDialogBinding.inflate(layoutInflater)

            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            dialogBinding.btnAdd.setOnClickListener {
                if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                    dialogBinding.etName.error = "Enter Name"
                }
                else if(dialogBinding.etRollno.text.toString().isNullOrEmpty()){
                    dialogBinding.etRollno.error="Enter RollNo"
                }
                else if(dialogBinding.etPhoneno.text.toString().isNullOrEmpty()){
                    dialogBinding.etPhoneno.error="Enter PhoneNo"
                }
                else {
                    userArray.add(UserModel(dialogBinding.etName.text.toString(),dialogBinding.etRollno.text.toString(),dialogBinding.etPhoneno.text.toString()))
                    dialog.dismiss()
                }
            }

            dialog.show()

        }
    }

    override fun editClick(position: Int) {
        var dialog = Dialog(this)
        var dialogBinding = EditBtnDialogeBinding.inflate(layoutInflater)

        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
//        dialogBinding.etName.setText("")
//        dialogBinding.etRollno.setText(userArray.toString())
//        dialogBinding.etPhoneno.setText(userArray.toString())
        dialogBinding.btnUpdate.setOnClickListener {
            if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                dialogBinding.etName.error = "Enter Name"
            }
            else if(dialogBinding.etRollno.text.toString().isNullOrEmpty()){
                dialogBinding.etRollno.error="Enter RollNo"
            }
            else if(dialogBinding.etPhoneno.text.toString().isNullOrEmpty()){
                dialogBinding.etPhoneno.error="Enter PhoneNo"
            }
            else {
                userArray[position]=(UserModel(dialogBinding.etName.text.toString(),dialogBinding.etRollno.text.toString(),dialogBinding.etPhoneno.text.toString()))
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun deleteClick(position: Int) {
        var alertDialog= AlertDialog.Builder(this)
        alertDialog.setTitle("Delete Item")
        alertDialog.setMessage("Do you want to delete the item?")
        alertDialog.setCancelable(false)
        alertDialog.setNegativeButton("No"){_,_->
            alertDialog.setCancelable(true)
        }
        alertDialog.setPositiveButton("Yes"){_,_->
            Toast.makeText(this, "The item is  deleted", Toast.LENGTH_SHORT).show()
            userArray.removeAt(position)
            listAdapter.notifyDataSetChanged()
        }
        alertDialog.show()
    }


}

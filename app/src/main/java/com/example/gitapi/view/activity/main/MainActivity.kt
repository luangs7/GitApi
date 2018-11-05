package com.example.gitapi.view.activity.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import br.com.luan2.lgutilsk.utils.*
import com.example.gitapi.App
import com.example.gitapi.R
import com.example.gitapi.model.GithubUser
import com.example.gitapi.model.viewmodel.GithubUserViewModel
import com.example.gitapi.retrofit.Resource
import com.example.gitapi.retrofit.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

     val presenter: MainActivityPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        presenter.injectView(this)
    }


    override fun showError(error: String) {
        showError(error)
    }

    override fun showSuccess(githubUser: GithubUser) {
        tvRepo.text = githubUser.repoUrl
        tvUsername.text = githubUser.username
        this.hideProgress()
//        ivProfile.loadUrl(githubUser.imageUrl)

    }


    fun handlerResource(result:Resource<GithubUser>){
        when(result.status){
            Status.SUCCESS -> result.data?.let {  this.showSuccess(it) }
            Status.LOADING -> this.showProgress("Buscando dados...")
            Status.ERROR -> result.message?.let {  showError(it) }
        }
    }

    override fun bindViews(viewModel: GithubUserViewModel) {
        viewModel.repositoryResult.observe(this, Observer { it -> it?.let { this.handlerResource(it) } })

        btnLoad.setOnClickListener { presenter.getUserInfo(edtUsername.getString()) }
        //        btnLoad.setOnClickListener { viewModel.getGithubUsers(edtUsername.getString()) }

    }

    override fun hideKeyboard() {
//        content?.hideKeyboard()
    }

    lateinit var snackbar: Snackbar

    override fun showProgress(message: String?) {

        this.snackbar = createSnackProgress("Buscando dados...")
        this.snackbar.show()
    }

    override fun hideProgress() {
        this.snackbar.dismissSnackProgress(this)
    }
}


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
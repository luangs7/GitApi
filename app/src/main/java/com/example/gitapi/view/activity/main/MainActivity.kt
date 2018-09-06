package com.example.gitapi.view.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.luan2.lgutilsk.utils.*
import com.example.gitapi.App
import com.example.gitapi.R
import com.example.gitapi.model.GithubUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).netComponent
                .injectMainActivity(this)

        presenter.injectView(this)
    }


    override fun showError(error: String) {
        onErrorAlert(error)
    }

    override fun showSuccess(githubUser: GithubUser) {
        tvRepo.text = githubUser.repoUrl
        tvUsername.text = githubUser.username
        ivProfile.loadUrl(githubUser.imageUrl)

    }

    override fun bindViews() {
        btnLoad.setOnClickListener { presenter.getUserInfo(edtUsername.getTextString()) }
    }

    override fun hideKeyboard() {

    }

    lateinit var snackbar: Snackbar

    override fun showProgress(message: String?) {

        this.snackbar = createSnackProgress("Buscando dados...")
    }

    override fun hideProgress() {
        this.snackbar.dismissSnackProgress(this)
    }
}

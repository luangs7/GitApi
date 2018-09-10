package com.example.gitapi.view.activity.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import br.com.luan2.lgutilsk.utils.*
import com.example.gitapi.App
import com.example.gitapi.R
import com.example.gitapi.model.GithubUser
import com.example.gitapi.view.activity.details.DetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.startActivity
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
        tvRepo.setOnClickListener {

            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("name",tvUsername.text)
            startActivity(intent)
        }
    }

    override fun hideKeyboard() {
        contentView?.hideKeyboard()
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
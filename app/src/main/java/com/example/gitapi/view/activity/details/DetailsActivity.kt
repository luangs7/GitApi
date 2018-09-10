package com.example.gitapi.view.activity.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.luan2.lgutilsk.utils.createSnackProgress
import br.com.luan2.lgutilsk.utils.dismissSnackProgress
import br.com.luan2.lgutilsk.utils.onAlertMessage
import br.com.luan2.lgutilsk.utils.onErrorAlert
import com.example.gitapi.App
import com.example.gitapi.R
import com.example.gitapi.adapter.OnClickRepository
import com.example.gitapi.adapter.RepoAdapter
import com.example.gitapi.model.Repo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsActivityContract.View, OnClickRepository {

    @Inject lateinit var presenter: DetailsActivityPresenter

    lateinit var adapter:RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        (application as App).netComponent
                .injectDetails(this)

        presenter.injectView(this)

    }

    override fun loadList(list: List<Repo>) {
        adapter = RepoAdapter(list,this)
        rvList.adapter = adapter
    }

    override fun bindComponents() {

        rvList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        presenter.getRepo(intent.getStringExtra("name"))
    }

    lateinit var snackbar: Snackbar

    override fun showLoading() {
        snackbar = createSnackProgress("Buscando dados...")
        snackbar.show()
    }

    override fun hideLoading() {
        snackbar.dismissSnackProgress(this)
    }

    override fun refreshList() {
        adapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        onErrorAlert(error)
    }


    override fun onClickRepoItem(repo: Repo) {
        onAlertMessage("Clicked ${repo.fullNameRepo}")
    }
}

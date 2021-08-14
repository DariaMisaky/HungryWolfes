import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.hungrywolfes.R
import com.example.hungrywolfes.ui.overview.OverviewViewModel
import com.example.hungrywolfes.ui.overview.Status
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageStatus")
fun bindStatus(
    statusImageView: CircleImageView, status: Status?
) {

    when (status) {
        Status.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_img)
        }
        Status.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
           // Log.d("cp","auzi ca eroare e ok")
        }
        Status.DONE -> {
            statusImageView.visibility = View.GONE
        }

    }
}
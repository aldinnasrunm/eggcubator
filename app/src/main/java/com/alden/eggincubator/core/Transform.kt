import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.alden.eggincubator.R

fun setParallaxTransformation(page: View, position: Float, ctx : Context){
var v : View = LayoutInflater.from(ctx).inflate(R.layout.onboarding_layout_item, null, false)
    val img = v.findViewById<ImageView>(R.id.img)
    page.apply {
        val parallaxView = img
        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                alpha = 1f
            position <= 1 -> { // [-1,1]
                parallaxView.translationX = -position * (width / 2) //Half the normal speed
            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                alpha = 1f
        }
    }

}
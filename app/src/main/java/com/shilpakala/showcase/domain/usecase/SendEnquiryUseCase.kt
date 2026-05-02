package com.shilpakala.showcase.domain.usecase

import android.net.Uri
import com.shilpakala.showcase.data.model.Artwork
import java.net.URLEncoder
import javax.inject.Inject

class SendEnquiryUseCase @Inject constructor() {

    fun buildWhatsAppMessage(artwork: Artwork): String {
        return """
            🙏 Namaste! I am interested in this artwork:
            
            *Product ID:* ${artwork.id}
            *Title:* ${artwork.title}
            *Material:* ${artwork.material}
            *Dimensions:* ${artwork.dimensions}
            *Price Range:* ${artwork.priceRange}
            *Heritage Style:* ${artwork.heritageStyle}
            
            Could you please share more details and availability?
            
            Thank you!
        """.trimIndent()
    }

    fun getWhatsAppUri(phoneNumber: String, artwork: Artwork): Uri {
        val message = buildWhatsAppMessage(artwork)
        val encodedMessage = URLEncoder.encode(message, "UTF-8")
        val cleanNumber = phoneNumber.replace("+", "").replace(" ", "")
        return Uri.parse("https://wa.me/$cleanNumber?text=$encodedMessage")
    }
}

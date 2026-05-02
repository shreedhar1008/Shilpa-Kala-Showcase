package com.shilpakala.showcase.data.local

import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleDataProvider @Inject constructor() {
    val artists = listOf(
        Artist(
            id = "a1",
            name = "Ramu Shilpi",
            bio = "Veteran stone carver from the ruins of Hampi, specializing in Dravidian architecture and idol making.",
            profileImageUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=800",
            specialty = "Stone Sculpture",
            location = "Hampi, Karnataka",
            whatsappNumber = "+919876543210"
        ),
        Artist(
            id = "a2",
            name = "Lakshmi Devi",
            bio = "Master of Madhubani painting, bringing centuries-old folklore to life through vibrant natural dyes.",
            profileImageUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=800",
            specialty = "Madhubani Painting",
            location = "Madhubani, Bihar",
            whatsappNumber = "+919876543211"
        ),
        Artist(
            id = "a3",
            name = "Venkatesh Achar",
            bio = "Third-generation bronze smith keeping the Chola lost-wax casting tradition alive.",
            profileImageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=800",
            specialty = "Bronze Casting",
            location = "Thanjavur, Tamil Nadu",
            whatsappNumber = "+919876543212"
        ),
        Artist(
            id = "a4",
            name = "Mallikarjun Sthapati",
            bio = "Expert temple architect and wood carver, known for intricate chariot designs.",
            profileImageUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=800",
            specialty = "Wood Carving",
            location = "Mysuru, Karnataka",
            whatsappNumber = "+919876543213"
        ),
        Artist(
            id = "a5",
            name = "Savitha Kumari",
            bio = "Reviving ancient pottery techniques with modern sustainable practices.",
            profileImageUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=800",
            specialty = "Terracotta Pottery",
            location = "Kutch, Gujarat",
            whatsappNumber = "+919876543214"
        )
    )

    val artworks = listOf(
        Artwork(
            id = "art_01",
            title = "Dhyana Buddha",
            description = "A serene Buddha in meditative pose, hand-carved from a single block of granite.",
            imageUrl = "https://images.unsplash.com/photo-1564399580075-5dfe19c205f3?w=800",
            artistId = "a1",
            category = ArtworkCategory.SCULPTURE,
            material = "Granite",
            dimensions = "24x18x12 inches",
            priceRange = "₹85,000",
            heritageStyle = "Dravidian"
        ),
        Artwork(
            id = "art_02",
            title = "Forest Folklore",
            description = "Intricate Madhubani depiction of a sacred forest clearing.",
            imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
            artistId = "a2",
            category = ArtworkCategory.PAINTING,
            material = "Handmade paper, Natural dyes",
            dimensions = "30x40 inches",
            priceRange = "₹35,000",
            heritageStyle = "Madhubani"
        ),
        Artwork(
            id = "art_03",
            title = "Nataraja",
            description = "The cosmic dancer in traditional Chola style bronze casting.",
            imageUrl = "https://images.unsplash.com/photo-1580060839134-75a5edca2e99?w=800",
            artistId = "a3",
            category = ArtworkCategory.METALWORK,
            material = "Bronze",
            dimensions = "18 inches height",
            priceRange = "₹1,20,000",
            heritageStyle = "Chola Bronze"
        ),
        Artwork(
            id = "art_04",
            title = "Gajendra Moksha",
            description = "Highly detailed wood relief carving of the elephant's liberation.",
            imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800",
            artistId = "a4",
            category = ArtworkCategory.SCULPTURE,
            material = "Teak wood",
            dimensions = "36x24 inches",
            priceRange = "₹55,000",
            heritageStyle = "Hoysala"
        ),
        Artwork(
            id = "art_05",
            title = "Desert Earth",
            description = "Traditional Kutch pottery with white clay geometric patterns.",
            imageUrl = "https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=800",
            artistId = "a5",
            category = ArtworkCategory.POTTERY,
            material = "Terracotta",
            dimensions = "12x12 inches",
            priceRange = "₹12,000",
            heritageStyle = "Kutch Terracotta",
            isWorkInProgress = true,
            completionPercentage = 75
        ),
        Artwork(
            id = "art_06",
            title = "Temple Guardian",
            description = "Stone Yali figure, a mythical creature guarding temple entrances.",
            imageUrl = "https://images.unsplash.com/photo-1546961342-ea5f62d8a7c3?w=800",
            artistId = "a1",
            category = ArtworkCategory.SCULPTURE,
            material = "Sandstone",
            dimensions = "48 inches height",
            priceRange = "₹1,50,000",
            heritageStyle = "Vijayanagara",
            isWorkInProgress = true,
            completionPercentage = 40
        ),
        Artwork(
            id = "art_07",
            title = "Life in Mithila",
            description = "A scene of village celebration in Bihar.",
            imageUrl = "https://images.unsplash.com/photo-1564399580075-5dfe19c205f3?w=800",
            artistId = "a2",
            category = ArtworkCategory.PAINTING,
            material = "Canvas, Acrylics",
            dimensions = "24x24 inches",
            priceRange = "₹15,000",
            heritageStyle = "Madhubani"
        ),
        Artwork(
            id = "art_08",
            title = "Procession Elephants",
            description = "Hand-painted clay elephants using traditional techniques.",
            imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
            artistId = "a5",
            category = ArtworkCategory.POTTERY,
            material = "Clay",
            dimensions = "8 inches height",
            priceRange = "₹4,500",
            heritageStyle = "Kutch Terracotta"
        ),
        Artwork(
            id = "art_09",
            title = "Eternal Flame Lamp",
            description = "Antique style brass lamp for home shrines.",
            imageUrl = "https://images.unsplash.com/photo-1580060839134-75a5edca2e99?w=800",
            artistId = "a3",
            category = ArtworkCategory.METALWORK,
            material = "Brass",
            dimensions = "12 inches",
            priceRange = "₹8,500",
            heritageStyle = "Traditional Brass"
        ),
        Artwork(
            id = "art_10",
            title = "Chariot Wheel",
            description = "A miniature replica of the Konark Sun Temple wheel.",
            imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800",
            artistId = "a4",
            category = ArtworkCategory.SCULPTURE,
            material = "Rosewood",
            dimensions = "15 inches diameter",
            priceRange = "₹22,000",
            heritageStyle = "Odissi"
        ),
        Artwork(
            id = "art_11",
            title = "Summer Raga",
            description = "A musical mood captured in paint.",
            imageUrl = "https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=800",
            artistId = "a2",
            category = ArtworkCategory.PAINTING,
            material = "Natural Pigments",
            dimensions = "12x12 inches",
            priceRange = "₹9,000",
            heritageStyle = "Madhubani",
            isWorkInProgress = true,
            completionPercentage = 90
        ),
        Artwork(
            id = "art_12",
            title = "Mountain Hermit",
            description = "Miniature stone carving of a sage.",
            imageUrl = "https://images.unsplash.com/photo-1546961342-ea5f62d8a7c3?w=800",
            artistId = "a1",
            category = ArtworkCategory.SCULPTURE,
            material = "Soapstone",
            dimensions = "6 inches height",
            priceRange = "₹3,500",
            heritageStyle = "Dravidian"
        ),
        Artwork(
            id = "art_13",
            title = "Golden Harvest",
            description = "Landscape painting of the Deccan fields.",
            imageUrl = "https://images.unsplash.com/photo-1564399580075-5dfe19c205f3?w=800",
            artistId = "a2",
            category = ArtworkCategory.PAINTING,
            material = "Oil on Canvas",
            dimensions = "48x36 inches",
            priceRange = "₹60,000",
            heritageStyle = "Modern Folk"
        ),
        Artwork(
            id = "art_14",
            title = "Sacred Vessel",
            description = "Ceremonial vessel with intricate engravings.",
            imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
            artistId = "a3",
            category = ArtworkCategory.METALWORK,
            material = "Copper",
            dimensions = "10 inches height",
            priceRange = "₹15,000",
            heritageStyle = "Traditional Copper"
        ),
        Artwork(
            id = "art_15",
            title = "Floral Mandala",
            description = "Decorative wooden wall hanging.",
            imageUrl = "https://images.unsplash.com/photo-1580060839134-75a5edca2e99?w=800",
            artistId = "a4",
            category = ArtworkCategory.SCULPTURE,
            material = "Mango wood",
            dimensions = "20 inches diameter",
            priceRange = "₹11,000",
            heritageStyle = "Decorative Wood"
        ),
        Artwork(
            id = "art_16",
            title = "Clay Chimes",
            description = "Set of hand-tuned terracotta wind chimes.",
            imageUrl = "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800",
            artistId = "a5",
            category = ArtworkCategory.POTTERY,
            material = "Clay",
            dimensions = "24 inches total length",
            priceRange = "₹2,500",
            heritageStyle = "Kutch Terracotta"
        ),
        Artwork(
            id = "art_17",
            title = "Goddess Durga",
            description = "Small bronze idol for personal altars.",
            imageUrl = "https://images.unsplash.com/photo-1604503468506-a8da13d82791?w=800",
            artistId = "a3",
            category = ArtworkCategory.METALWORK,
            material = "Panchaloha",
            dimensions = "5 inches height",
            priceRange = "₹18,000",
            heritageStyle = "Chola Bronze"
        ),
        Artwork(
            id = "art_18",
            title = "Ancient Script",
            description = "Stone slab with decorative script patterns.",
            imageUrl = "https://images.unsplash.com/photo-1546961342-ea5f62d8a7c3?w=800",
            artistId = "a1",
            category = ArtworkCategory.SCULPTURE,
            material = "Black stone",
            dimensions = "12x18 inches",
            priceRange = "₹25,000",
            heritageStyle = "Vijayanagara"
        ),
        Artwork(
            id = "art_19",
            title = "Monsoon Clouds",
            description = "Abstract painting inspired by the western ghats.",
            imageUrl = "https://images.unsplash.com/photo-1564399580075-5dfe19c205f3?w=800",
            artistId = "a2",
            category = ArtworkCategory.PAINTING,
            material = "Watercolors",
            dimensions = "18x24 inches",
            priceRange = "₹12,500",
            heritageStyle = "Modern Folk"
        ),
        Artwork(
            id = "art_20",
            title = "Rustic Bowl",
            description = "Heavy terracotta bowl for decoration.",
            imageUrl = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800",
            artistId = "a5",
            category = ArtworkCategory.POTTERY,
            material = "Terracotta",
            dimensions = "14 inches diameter",
            priceRange = "₹5,000",
            heritageStyle = "Kutch Terracotta"
        )
    )
}

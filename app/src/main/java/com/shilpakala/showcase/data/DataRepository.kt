package com.shilpakala.showcase.data

import com.shilpakala.showcase.data.models.Artist
import com.shilpakala.showcase.data.models.HeritageStyle
import com.shilpakala.showcase.data.models.Sculpture
import com.shilpakala.showcase.data.models.TimelineStep

object DataRepository {

    private fun createTimelineSteps(artistPrefix: String): List<TimelineStep> = listOf(
        TimelineStep(
            id = "${artistPrefix}_step_1",
            stepNumber = 1,
            title = "Raw Stone Selection",
            description = "The master craftsman personally visits quarries to select the finest blocks of stone. Each piece is carefully examined for grain consistency, color uniformity, and structural integrity. The right stone is the foundation of a great sculpture — it must sing when tapped, indicating no hidden fractures.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBsrggFjz8SV40JM152Vy-TZ_PzAGu2R1DdD-A3edf4x2W7bDLlNa2yGAKhERcq_nPriAmCikpbstaSjZipJ5ebhmew54mYKQ60q9EVuiLhpid_kEgpsrO-ge-WLHxmxNpAjXT97x9ZPWli5jkMPjZJOR7dpHs-I0Na33YVMwlaBPTS8jgLxeHRhrgv4fjP1JVQav6aMvdLcZZioOS60UeATctmt8tXwJxyPELwuvjh3zvu1NvcGW7HiqE3_zA0gRpFcAgcfesUV3KZ"
        ),
        TimelineStep(
            id = "${artistPrefix}_step_2",
            stepNumber = 2,
            title = "Rough Shaping",
            description = "Using heavy chisels and hammers, the sculptor removes large chunks of excess stone to reveal the basic form. This stage requires immense strength and vision — the artist must see the finished sculpture within the raw block. Traditional measurement techniques passed down through generations guide the proportions.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCWSGM8Q5KR1n85VJvHlh-5xddC3DVuOvONCrCG8pGVH5OSP0iJCp_LZrumiwdQhbJrtwO2b2E00p4zMN1YMei_fei_is91K5H6GG8HTeBj4X2_rHubWg8lX_LDnAZoIEXKTzW_fdyJXyhKB6lMYDV_N_davEXX5KDQVQI1U_Efzm1bmelvV-qqLJ3nG38IcXHjRxtcLKR7VIbkVz8vhkOL9eFsw97PJNR3Ihg-1Me25D6Bi1mBwC1mfSlIe_Rxb06w3J-0LdSUEBO8"
        ),
        TimelineStep(
            id = "${artistPrefix}_step_3",
            stepNumber = 3,
            title = "Detailed Carving",
            description = "The most time-intensive phase where intricate details emerge. Fine chisels, rasps, and specialized tools bring out delicate features — ornamental jewelry, flowing garments, facial expressions, and decorative motifs. A single deity sculpture can take 3-6 months at this stage alone.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCWq0Kb7n--Hg-m1ZPKLx_JBpGVPrl85AVcVO1YOKz7OtsqeFDcrOPo11kXo5gAlegIl6l_jv3hnY32Uc_EmGJqLj_zrmq-djRsN_C1_kOIvTvq0khOIrlpSaYFcTrNdrXN85r6iOjJBDdDPwiZzCHk-rcDXGxv0jV6Pv4qi_T-0sYxQxH0ukq1NXmsC5xkU6K5milrcvtjSoGcSeVk08tCzbu1a9mFRiG1cOU1QOVZnqgRcUggODKt2xXX7bV8_ic9j-h-yl4CJEJR"
        ),
        TimelineStep(
            id = "${artistPrefix}_step_4",
            stepNumber = 4,
            title = "Polishing",
            description = "Progressive sanding and polishing using increasingly fine abrasives transforms rough stone into a mirror-like finish. Natural stone oils are applied to enhance the grain and color. The sculpture begins to glow with an inner warmth that only master-polished stone can achieve.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCecGlvVqpUBAROKXYpWOEBQbrAmNbDRxzBOnQMG5u5ItDy1PpLcKWkx7OxW1tMILl6wh9cRXpivqn8kSsLft8EJnsd_0330SUaOIIirMuNPKFzsdQl-hRxUDrKSUX88LsLXBi7dBnLHOwwoRNWwuXfWHjRhHjM_GsHPCfMufjuTMLH_fl3vIbHFOl56Uw8EV4NijqYDLM9hilYqVUE1ssSZtz50NWr-WbPgUSdmEv4pAwiPYTNcTiLtoVS2pkdpWeFqoYGFWKyCUwP"
        ),
        TimelineStep(
            id = "${artistPrefix}_step_5",
            stepNumber = 5,
            title = "Finishing & Blessing",
            description = "Final touches are applied — eyes are opened (netra unmīlana), and the sculpture undergoes a traditional blessing ceremony (prāṇa pratiṣṭhā). The Shilpi performs rituals to infuse divine energy, transforming stone into a sacred embodiment. The masterpiece is now ready for its forever home.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB9ab57WyT0b7STNKVI901YFVFPnT4tZqoM72IPxvKc1tRg01oUvPtrVYtnBhad8AkRxbrPoJX-si4lKjHvO0h_6UB6TVmT_rrCGUZfiDyjiDq9Jd-sOGa7uEKry5ghol3DeQ9tLzKkv8in-uYIiQ12QrmDR8XTRJ_imMuL9faKRw5Cf6fTJhCmdfLRaIZTGe8PMjcUrcf1RczQTvd82EDIuxejRF2CAFoj0LVNQNsCqD71O4im6I23cVghrjGtjfxcVTQRsoB5fqZw"
        )
    )

    val sculptures = mutableListOf<Sculpture>()

    var artists: MutableList<Artist> = mutableListOf(
        Artist(
            id = "artist_1",
            name = "Ramu Shilpi",
            location = "Mysuru, Karnataka",
            specialty = "Hoysala Stone Carving",
            bio = "A fourth-generation stone carver from the legendary Shilpi lineage of Mysuru, Ramu has dedicated over 35 years to mastering the intricate Hoysala style, known for its incredibly detailed temple sculptures. His work adorns major temples across South India, including several recent restoration projects at the Belur and Halebidu complexes. Ramu specializes in black granite deity sculptures that feature the characteristic Hoysala scrollwork and delicate filigree patterns. Beyond his own creations, he runs a traditional 'Gurukula' where he trains aspiring sculptors in the ancient 'Shilpa Shastra' texts, ensuring that these centuries-old artistic traditions are preserved for future generations. His mastery of the chisel allows him to create textures in stone that mimic the softest silk and the most complex lace, earning him the prestigious Shilpakala Academy Award for lifetime achievement.",
            profileImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDD50dsNO7ZZIJrAowhMvEc_TJZCW4TG6ys7QZ_AaUwgMj5x5e7QLAlJ_WxRFZajfA2PlgkvkSNtCzq-FEu5PQkQj7LksKcmtvoJ4Lk9l_SQHTjypbmO7_id0n4n13tCsScUo_7BuiV5M7ktdY36_KOvFwtN63dIIWAH1iBF7JWlB7zaqNstfC5FjJTDp9SVkOIEL9WmikBvLnUzpePf4tDZUTB4tQuXYEzi-5cWUjdt0fXRTqmPaTsiKhTQOiz8zenG4SzU2iPkTyA",
            whatsappNumber = "919876543210",
            sculptures = listOf(
                Sculpture(
                    id = "sculpt_1",
                    productId = "SKS-001",
                    title = "Lord Ganesha - Hoysala Style",
                    description = "A magnificent representation of Lord Ganesha in the classic Hoysala style. This 2.5-foot sculpture features intricate scrollwork around the base, detailed jewelry patterns, and the characteristic Hoysala arch framing. Carved from premium black granite sourced from the Chamundi Hills region. Each ornament and fold of the garment has been painstakingly detailed over 4 months of dedicated work.",
                    style = "Hoysala",
                    material = "Black Granite",
                    priceRange = "12th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/ganesha",
                    isAvailable = true,
                    artistId = "artist_1"
                ),
                Sculpture(
                    id = "sculpt_4",
                    productId = "SKS-004",
                    title = "Goddess Saraswati",
                    description = "Goddess Saraswati, the deity of knowledge and arts, seated on a lotus pedestal playing the veena. Her four arms hold sacred texts, a mala, and the veena with incredible detail — individual strings are visible on the instrument. The flowing garments and elaborate crown showcase the finest Hoysala decorative traditions. White soapstone gives her an ethereal, luminous quality.",
                    style = "Hoysala",
                    material = "White Soapstone",
                    priceRange = "12th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/saraswati",
                    isAvailable = true,
                    artistId = "artist_1"
                ),
                Sculpture(
                    id = "sculpt_6",
                    productId = "SKS-006",
                    title = "Makara Torana - Arch Panel",
                    description = "An ornamental Makara Torana arch panel featuring the mythical Makara creature. This decorative panel is designed to frame doorways or deity niches. The intricate scrollwork, floral patterns, and mythical creatures showcase the highest level of Hoysala ornamental carving. Can be installed in temples, prayer rooms, or as a standalone art piece.",
                    style = "Hoysala",
                    material = "Sandstone",
                    priceRange = "13th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAL_HU_wrfyM1fo1aQl8yO9Hbt0U36cgSPnxboiYU-hz8k67aKO0PpQgEiLV5Plk3b6dNYwpaAIgyrK09NN4sPy4_DAn0IBh6raKUs4q5PrlT2QEXe8KRFyB63JHOTCOUEUBm5YpFyB0vjFpHweUd0JWt0gkE2G9AAQKveWP3Ze7bqVTpt9kW_EKN1Px2cr2iBsUNg5t3Xh_25GMYa5NFL4D4FiStS_lThBH3WayWr67oTaYBo1iKWzjLlcpLarrwuttPOu58yy08ez",
                    isAvailable = true,
                    artistId = "artist_1"
                ),
                Sculpture(
                    id = "sculpt_18",
                    productId = "SKS-018",
                    title = "Apsara - Celestial Dancer",
                    description = "A graceful Apsara (celestial dancer) in a dynamic dance pose, adorned with elaborate jewelry and a flowing lower garment. The figure stands on a lotus pedestal with one leg raised in a dance position. The sensuous curves, delicate hand gestures (mudras), and joyful expression embody the artistic excellence of Chalukya temple sculpture. The ornamental headdress features tiny carved flowers.",
                    style = "Chalukya",
                    material = "Soapstone",
                    priceRange = "12th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCKUfbl9awap3wjuEqDn3hT6WykV_A-yeZoj2hTTj1_9jRS8v0EHtdo9jQDRCppjeMwHfrtUXwYdEuhjriJ8-YeoXRVqPtrJImRbspEmFii51TXAgfE8i4X0QeKyOr89IIJfsaxqS-ewimmgnj2VJ7pImO-4fyyfXoY8WzA8Qw1nLkRiMEuqS5DtFjb0ZTi41vW7-KlR8NRiTx3HPIqgqz2EDDQ8igcnVM9dEUFeNFPle671ckMVk6idh0L-zAcV1F3fPO_n-Exaf8g",
                    isAvailable = true,
                    artistId = "artist_1"
                )
            ),
            timelineSteps = createTimelineSteps("ramu")
        ),
        Artist(
            id = "artist_2",
            name = "Venkatesh Acharya",
            location = "Udupi, Karnataka",
            specialty = "Dravidian Wood Carving",
            bio = "Master wood carver Venkatesh Acharya hails from the sacred temple town of Udupi, where the tradition of religious wood carving has flourished for over seven centuries. Trained from the age of ten under his grandfather, a renowned temple architect (Sthapati), Venkatesh specializes in teak, rosewood, and sandalwood deity sculptures in the classical Dravidian style. His work is internationally recognized for its warmth, flowing lines, and the masterful way he incorporates natural wood grain patterns into his anatomical designs. He has been the lead craftsman for several major temple chariot (Ratha) projects across Karnataka and Tamil Nadu. Venkatesh is particularly known for his spiritual approach to his craft, often performing meditative rituals before beginning a new sculpture to align his creative vision with the essence of the divine form he seeks to reveal from the timber.",
            profileImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDnTpzjQfCLChYe-DloyoRCzopet_20AJpWOkAkyMpjHSU6oJOFN406kjkUZZ-e993xoifYleEpabd6rPwagHPUvztWlVmRbfrQSyTtaHsoJnlpQYlGTwN_XGBcDmUx8Ek88NkQQ-aqJLK5o3oYmCPstTxlhnKQfwsnIB16YWfi_fgPTuLiESeJwfkpWDAODQQxAxZQE9wQAeWOCZihDE_Ih8MU_8Xw9Q-bWCIAMAHvcrwwiWd7NM0jJ4kXpnnqspGouKBnVsQ-PpM3",
            whatsappNumber = "919876543211",
            sculptures = listOf(
                Sculpture(
                    id = "sculpt_7",
                    productId = "SKS-007",
                    title = "Shiva Linga with Naga",
                    description = "A beautifully carved Shiva Linga with a five-hooded Naga serpent serving as a canopy. The Linga sits on an intricately decorated yoni base with water drainage channel. The Naga's scales are individually carved with remarkable precision. Made from aged teak wood that has been seasoned for 5 years, ensuring no warping or cracking over time.",
                    style = "Dravidian",
                    material = "Teak Wood",
                    priceRange = "9th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/shivalinga",
                    isAvailable = true,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_2",
                    productId = "SKS-002",
                    title = "Nandi Bull - Dravidian Style",
                    description = "The sacred bull Nandi, vehicle of Lord Shiva, rendered in the powerful Dravidian style. This sculpture captures Nandi in a seated position with elaborate decorative bells, garlands, and a ornate saddle cloth. The muscular form conveys strength while the serene expression reflects divine devotion. Polished to a mirror finish that reflects the natural veining of the granite.",
                    style = "Dravidian",
                    material = "Black Granite",
                    priceRange = "11th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBNGUAPuEmeJHZ_G8V3MB639H-qwuYhikNl1K-INyjc1CFkgK2BLt7oSrQevB4uxLSV6fXU1-cP58vWa6Im1qxYdwIueYi6J5T70H3407Z9YAC34am425NdlCtAmUQ4n-QrDl19ow9sJwP5XjBfnpH6tXysMY4zFWEia-NeGQNWeXnm5f4D_8JeBRfa_wKhNg68P0UKH8R-hRPN17LiyLHIrIZ8L3VAEO3iOMkfRkEM_nyxoe_t-AjnIAm7TAQMoAjHCIQ4nVeHYUNb",
                    isAvailable = true,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_3",
                    productId = "SKS-003",
                    title = "Dancing Nataraja",
                    description = "Lord Shiva as Nataraja, the cosmic dancer, performing the Tandava dance within the ring of fire (prabhavali). This dynamic sculpture captures the exact moment of divine dance — the raised leg, flowing locks, and four arms each holding symbolic objects. The flame ring features 32 individual flames, each uniquely carved. A true masterpiece that took 6 months to complete.",
                    style = "Hoysala",
                    material = "Soapstone",
                    priceRange = "11th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCKUfbl9awap3wjuEqDn3hT6WykV_A-yeZoj2hTTj1_9jRS8v0EHtdo9jQDRCppjeMwHfrtUXwYdEuhjriJ8-YeoXRVqPtrJImRbspEmFii51TXAgfE8i4X0QeKyOr89IIJfsaxqS-ewimmgnj2VJ7pImO-4fyyfXoY8WzA8Qw1nLkRiMEuqS5DtFjb0ZTi41vW7-KlR8NRiTx3HPIqgqz2EDDQ8igcnVM9dEUFeNFPle671ckMVk6idh0L-zAcV1F3fPO_n-Exaf8g",
                    isAvailable = false,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_11",
                    productId = "SKS-011",
                    title = "Temple Door Panel - Ashtalakshmi",
                    description = "A magnificent door panel depicting the eight forms of Goddess Lakshmi (Ashtalakshmi). Each form is carved in an individual niche surrounded by floral scrollwork. This panel measures 4 feet by 2 feet and is designed as a functional temple or pooja room door panel. The depth of carving creates dramatic shadow play in natural light.",
                    style = "Dravidian",
                    material = "Teak Wood",
                    priceRange = "11th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/ashtalakshmi",
                    isAvailable = true,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_8",
                    productId = "SKS-008",
                    title = "Garuda in Flight",
                    description = "The divine eagle Garuda, vehicle of Lord Vishnu, captured mid-flight with wings fully spread. This dynamic wood sculpture showcases individual feather carving on each wing — over 200 feathers in total. The powerful talons and noble expression convey both majesty and devotion. Carved from a single block of premium rosewood.",
                    style = "Dravidian",
                    material = "Rosewood",
                    priceRange = "18th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDuTf1_9I29vVDi5tciho82LFOCRripQLiENCuovWGOzZutGOyNiwtC9FeiL7HQrGMCp90AnV0IsxK42RO4voj7k5Vb-Wq6s3LC_F7C20tidsuI0ugxKitfqVHAIMF6q03nfkGXo1YdjnDSoa0ZXDULY38trnzMLNdV12C1UftLN86-JQSaIpVLWZh77nU-FoxlcA7XJwV1T7yz6yHvwCf8N43--LVRhvUZAdJew6WqRgmoY_JqN6X3i9_4mvxkohSRNfnMdO8eJmXQ",
                    isAvailable = true,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_9",
                    productId = "SKS-009",
                    title = "Krishna Playing Flute",
                    description = "Young Lord Krishna standing in the iconic tribhanga pose, playing his divine flute. This charming 2-foot sculpture captures the playful grace of the butter thief turned divine musician. The peacock feather crown, gentle smile, and cross-legged stance are rendered with loving attention to detail. The natural rosewood grain enhances the warmth of this beloved deity.",
                    style = "Dravidian",
                    material = "Rosewood",
                    priceRange = "17th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/krishna",
                    isAvailable = true,
                    artistId = "artist_2"
                ),
                Sculpture(
                    id = "sculpt_12",
                    productId = "SKS-012",
                    title = "Hanuman - Veera Pose",
                    description = "Lord Hanuman in the heroic Veera (warrior) pose, lifting Mount Dronagiri with one hand. This powerful composition captures the devotee-warrior's incredible strength and unwavering devotion. The musculature, flowing tail, and wind-swept garments create a sense of dynamic movement frozen in wood. A favorite among devotees.",
                    style = "Dravidian",
                    material = "Sandalwood",
                    priceRange = "16th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/hanuman",
                    isAvailable = true,
                    artistId = "artist_2"
                )
            ),
            timelineSteps = createTimelineSteps("venkatesh")
        ),
        Artist(
            id = "artist_4",
            name = "K. Somnath",
            location = "Bengaluru, Karnataka",
            specialty = "Heritage Design & Carving",
            bio = "K. Somnath is a veteran sculptor with over 40 years of experience in both Dravidian wood carving and Chalukya stone traditions. Based in Bengaluru, he is known for his versatility and his deep understanding of multi-media traditional arts. Somnath has spent decades documenting rare temple motifs across India and is a frequent consultant for major heritage conservation projects. His work is characterized by its technical precision, scholarly research, and strict adherence to ancient canonical proportions defined in the Agama texts.",
            profileImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBLk6jyymuDETRMklRU0YlkfGSVa2Z9R0msfD0dIpollIohDWtLcDUfBsnBbCBo6QYpMNAMGGTtAhCKcBVsxihjmkVXgUTgvEO1fkxbCaRlHCIqC8R4Fy_onoZH-ky_zqgqJDHPw7fxdchpvWzIJq8AW3OF2lEciINN2wOTgYPbs_Dqzwfk0ht7j4pDIYv4qeovG0-Ol3rPtOSttnEyaFPkdDlIAV9EL2R20wuEean1daTvzrQPoEMacQo0phQNgW1UT8B9TKxAv8sE",
            whatsappNumber = "919876543214",
            sculptures = listOf(
                Sculpture(
                    id = "sculpt_13",
                    productId = "SKS-013",
                    title = "Ratha Chariot Wheel",
                    description = "A decorative replica of the iconic temple chariot wheel, inspired by the Konark Sun Temple. This wall-mounted piece features 12 spoke designs representing the months, with intricate geometric patterns filling each segment. The hub features a lotus medallion. Makes a stunning wall art piece with deep cultural significance.",
                    style = "Dravidian",
                    material = "Teak Wood",
                    priceRange = "13th Century CE",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuByxHKEmG_NROqyNtAo8NJ0iey8w46qZTpvH1r2YLI8EvJnYbvg4Ru8Fwa-9Tvsvlh4hmZTl-0FZuxkCCbqiQpxOKNHB2T2XgVfzpCP4HxgFIuvTBUuI-URiLoxNKbLl7_BTlyb1FAVDRJuAiJWErc3osbaAdE1qBF4yyJFrt2aBdJ-8dQFJgwXXSJmwjyObHZiqvCB9a3H-7qOrIt-u5gCzvI4Ps5ytoa7lqTftwUrXq2SBOIhj-xbDxZCraV2rFnMty8a26y3FIbm",
                    isAvailable = true,
                    artistId = "artist_4"
                ),
                Sculpture(
                    id = "sculpt_16",
                    productId = "SKS-016",
                    title = "Surya - The Sun God",
                    description = "Surya, the Sun God, standing in a chariot drawn by seven horses representing the seven days of the week. This relief panel sculpture combines high and low relief techniques to create a sense of depth and movement. The radiating crown and lotus flowers at his feet are characteristic Chalukya motifs. A stunning piece that catches and plays with natural light.",
                    style = "Chalukya",
                    material = "Sandstone",
                    priceRange = "13th Century CE",
                    imageUrl = "android.resource://com.shilpakala.showcase/drawable/surya",
                    isAvailable = true,
                    artistId = "artist_4"
                )
            ),
            timelineSteps = createTimelineSteps("somnath")
        )
    )

    val heritageStyles: List<HeritageStyle> = listOf(
        HeritageStyle(
            name = "Hoysala",
            origin = "Belur & Halebidu, Karnataka",
            period = "11th - 14th Century CE",
            description = "The Hoysala architectural and sculptural style represents one of the highest achievements of Indian stone carving. Developed under the patronage of the Hoysala dynasty, this style is characterized by its incredibly intricate and detailed surface ornamentation. Every inch of temple walls was covered with sculptures, friezes, and decorative motifs of extraordinary finesse. The temples of Belur, Halebidu, and Somnathpura remain standing testaments to this tradition's excellence. Hoysala sculptors worked primarily in soft soapstone (chloritic schist) which allowed for extremely fine detail work — individual jewelry beads, fabric textures, and hair strands are visible in surviving sculptures.",
            characteristics = listOf(
                "Extraordinarily detailed surface ornamentation covering every available surface",
                "Star-shaped temple floor plans unique to this style",
                "Lathe-turned decorative pillars with mirror-polish finish",
                "Narrative friezes depicting epics from Ramayana and Mahabharata",
                "Distinctive bracket figures (madanikas) in sensuous poses",
                "Use of soft soapstone (chloritic schist) enabling fine detail",
                "Horizontal layered decoration on temple walls (seven bands)",
                "Individual sculptors signed their work — rare in Indian temple art"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAio-wZUPwpjyDqG_EzJi-pltxyug57MAs6LMLObZHiBUxVxtyGvvRwiugOOmhLfz-qLbZMBmhHkBFR_oKApit-DLiVdWmUc1MfL2tIWWotSHSP2E0UJeMK3RykXvhhPWcKMXzdIAiar4Ph5YLNyRjhCps_To9NNf3_VU-5Jsmd9MfETQY_ZUwPIEgqBtlNss13gxrZroEt-h5_kn4GzgKXb_5iw6rPThb-xDzb5h9GMd-lOG0Sa3E3TbqHRoGJ_-8n59iPI_eZkmgP"
        ),
        HeritageStyle(
            name = "Dravidian",
            origin = "Tamil Nadu & Southern Karnataka",
            period = "7th - 18th Century CE",
            description = "The Dravidian style is one of the most enduring and influential architectural traditions in India, spanning over a millennium of continuous development. Originating with the Pallava dynasty at Mahabalipuram, it was refined by the Chola dynasty and reached monumental proportions under the Vijayanagara and Nayaka kingdoms. This style is characterized by towering gopurams (gateway towers), massive pillared halls, and elaborate sculptural programs that narrate Hindu mythology. The Dravidian tradition emphasizes verticality, symmetry, and the integration of sculpture with architecture. Unlike the Hoysala style's intricate surface decoration, Dravidian sculpture achieves its impact through monumental scale and powerful, idealized forms.",
            characteristics = listOf(
                "Towering pyramidal gopurams (gateway towers) covered with sculptures",
                "Monolithic rathas (chariot-shaped temples) and mandapams",
                "Massive Nandi bull sculptures at Shiva temples",
                "Bronze casting tradition for processional deity images",
                "Pillared corridors (prakarams) with sculpted horse and yali columns",
                "Stone chariot wheels as architectural decorative elements",
                "Elaborate ceiling panels with lotus medallions and narrative scenes",
                "Integration of music, dance, and dramatic arts into temple sculpture"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBdD1IKiM743cndcbSQqiuMJoHdIqYQw83ohazWeDtNkn8lsgWdLXWawyErFisAlaiDrCpDW_YHyQ9yJnMHqpWfgpAtObnopRUYOwuVXV5HJmfwF79DVyw6OV0TcSwnQNrf4kPop8pAGd-gDe-LhYiQ0V7oJAqXXwnAyKlSX70sU96pRYNwO6lAOIyu5ov-DPm85NXXkILnROVPOdfab06SBu2YrxPeA4mCF9QLvLJWELdmFnyzdmS80uKFkdYT3BI0ST5lHVc1QiYo"
        ),
        HeritageStyle(
            name = "Chalukya",
            origin = "Badami, Aihole & Pattadakal, Karnataka",
            period = "6th - 12th Century CE",
            description = "The Chalukya style emerged in the Deccan region of Karnataka, blending northern (Nagara) and southern (Dravidian) architectural influences into a distinctive regional expression. The early Chalukyas of Badami created remarkable rock-cut cave temples at Badami and some of the earliest structural stone temples at Aihole — often called the 'cradle of Indian architecture.' The later Western Chalukyas of Kalyani further refined this style with more ornate decoration. Chalukya sculpture is known for its powerful, well-proportioned figures with a naturalistic quality. The sculptors excelled in both high relief and fully three-dimensional work, creating dramatic compositions that convey movement and emotion.",
            characteristics = listOf(
                "Fusion of Northern (Nagara) and Southern (Dravidian) temple styles",
                "Rock-cut cave temples with sophisticated relief sculptures",
                "Experimental temple forms — Aihole has 125+ temples in various styles",
                "Naturalistic figure proportions with dynamic poses",
                "Elaborate door-frame carvings with river goddess figures",
                "Distinctive kirtimukha (face of glory) decorative motif",
                "Miniature decorative tower motifs on temple walls",
                "Rich narrative panels depicting Puranic stories with emotional depth"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDxHeePBmahb4c2aJ9rMfS2iWzSRJ3HY4D54MPcDcrsircFM3Qp-AgZk-vI4il67ZnL0Hn7Dk2OKD3wIx5Jbk7eKwZDErGVYhG9Z_jSSNmYBaf1r3LT_JDLuOcvbRKqy5jfmfgh__b4YBSkFRYQGOrhs47xim_FCsRC8ni9tR-mC6RwcGH0-qaRYraRZpqN9tCPtF7eJ4E6D5k33S-9sK8utKJvst2AslKFwkQwMv8_jMDOZg8FsljIeqEeLorONhIkE9PR9rdxREgg"
        )
    )

    fun getArtistById(artistId: String): Artist? = artists.find { it.id == artistId }

    fun getSculptureById(sculptureId: String): Sculpture? {
        for (artist in artists) {
            val sculpture = artist.sculptures.find { it.id == sculptureId }
            if (sculpture != null) return sculpture
        }
        return null
    }

    fun getArtistBySculptureId(sculptureId: String): Artist? {
        return artists.find { artist ->
            artist.sculptures.any { it.id == sculptureId }
        }
    }

    fun getFeaturedSculptures(): List<Sculpture> {
        return artists.flatMap { it.sculptures }.filter { it.isAvailable }.take(8)
    }

    fun addSculpture(sculpture: com.shilpakala.showcase.data.models.Sculpture) {
        sculptures.add(sculpture)
    }

    fun registerArtist(artist: com.shilpakala.showcase.data.models.Artist) {
        artists.add(artist)
    }
}

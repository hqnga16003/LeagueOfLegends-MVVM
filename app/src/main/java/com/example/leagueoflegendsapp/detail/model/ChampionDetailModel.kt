import com.example.leagueoflegendsapp.champion.ImageModel
import com.example.leagueoflegendsapp.champion.InfoModel
import com.example.leagueoflegendsapp.detail.model.PassiveModel
import com.example.leagueoflegendsapp.detail.model.SkinModel
import com.example.leagueoflegendsapp.detail.model.SpellModel
import com.example.leagueoflegendsapp.champion.StatsModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailModel(
    @SerialName("id") val id: String,
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("title") val title: String,
    @SerialName("image") val image: ImageModel,
    @SerialName("skins") val skins: List<SkinModel>,
    @SerialName("lore") val lore: String,
    @SerialName("blurb") val blurb: String,
    @SerialName("allytips") val allyTips: List<String>,
    @SerialName("enemytips") val enemyTips: List<String>,
    @SerialName("tags") val tags: List<String>,
    @SerialName("partype") val parType: String,
    @SerialName("info") val info: InfoModel,
    @SerialName("stats") val stats: StatsModel,
    @SerialName("spells") val spells: List<SpellModel>,
    @SerialName("passive") val passive: PassiveModel,
    @SerialName("recommended") val recommended: List<String>,
)
package cat.institutmontilivi.exercicinavegacioambbarrainferior.navegacio

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import cat.institutmontilivi.exercicinavegacioambbarrainferior.R

enum class CategoriaDeNavegacio(
    val rutaPrevia: String,
    val icona: Int,
    @StringRes val titol: Int
){
    CategoriaGuerrers("CategoriaGuerrers", R.drawable.sword_vector, R.string.categoria_de_guerrers),
    CategoriaOrdinadors("CategoriaOrdinadors", R.drawable.computer_vector, R.string.categoria_d_ordinadors),
    CategoriaAliens("CategoriaAliens", R.drawable.alien_vector, R.string.categoria_d_aliens),
}

sealed class Destinacio(
    val rutaBase: String,
    val argumentsDeNavegacio: List<ArgumentDeNavegacio> = emptyList()
){
    val navArgs = argumentsDeNavegacio.map{it.toNavArgument()}

    val rutaGenerica = run{
        //rutabase({arg1}/{arg2}/...
        val clausArguments = argumentsDeNavegacio.map{"{${it.clau}}"}
        listOf(rutaBase)
            .plus(clausArguments)
            .joinToString ("/")
    }

    object LlistaGuerrers: Destinacio(CategoriaDeNavegacio.CategoriaGuerrers.rutaPrevia + "/LlistaGuerrers")
    object DetallsGuerrer:Destinacio(CategoriaDeNavegacio.CategoriaGuerrers.rutaPrevia + "/DetallsGuerrer" , listOf(ArgumentDeNavegacio.IdGuerrer)){
        fun CreaRutaAmbArguments (idGuerrer: Int) = "$rutaBase/$idGuerrer"
    }

    object LlistaOrdinadors: Destinacio(CategoriaDeNavegacio.CategoriaOrdinadors.rutaPrevia + "/LlistaOrdinadors")
    object DetallsOrdinador:Destinacio(CategoriaDeNavegacio.CategoriaOrdinadors.rutaPrevia + "/DetallsOrdinador" , listOf(ArgumentDeNavegacio.IdOrdinador)){
        fun CreaRutaAmbArguments(idOrdinador: Int) = "$rutaBase/$idOrdinador"
    }

    object LlistaAliens: Destinacio(CategoriaDeNavegacio.CategoriaAliens.rutaPrevia + "/ListaAliens")
    object DetallsAlien:Destinacio(CategoriaDeNavegacio.CategoriaAliens.rutaPrevia + "/DetallsAlien" , listOf(ArgumentDeNavegacio.IdAlien)){
        fun CreaRutaAmbArguments(idAlien: Int) = "$rutaBase/$idAlien"
    }

}

//NavType<*> ens permet identificar qualsevol tipus de NavType
enum class ArgumentDeNavegacio (val clau: String, val tipus: NavType<*>){
    IdGuerrer("IdGuerrer", NavType.IntType),
    IdOrdinador("IdOrdinador", NavType.IntType),
    IdAlien("IdAlien", NavType.IntType);

    fun toNavArgument (): NamedNavArgument {
        return navArgument(clau) {type = tipus}
    }
}
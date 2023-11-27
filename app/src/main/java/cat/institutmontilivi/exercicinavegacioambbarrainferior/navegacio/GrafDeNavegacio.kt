package cat.institutmontilivi.exercicinavegacioambbarrainferior.navegacio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import cat.institutmontilivi.exercicinavegacioambbarrainferior.dades.Aliens
import cat.institutmontilivi.exercicinavegacioambbarrainferior.dades.Guerrers
import cat.institutmontilivi.exercicinavegacioambbarrainferior.dades.Ordinadors
import cat.institutmontilivi.exercicinavegacioambbarrainferior.navegacio.Destinacio
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaAlien
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaGuerrer
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaLlistaAliens
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaLlistaGuerrers
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaLlistaOrdinadors
import cat.institutmontilivi.exercicinavegacioambbarrainferior.ui.pantalles.PantallaOrdinador

@Composable
fun GrafDeNavegacio(controladorDeNavegacio: NavHostController = rememberNavController()){
    NavHost(navController = controladorDeNavegacio, startDestination = CategoriaDeNavegacio.CategoriaGuerrers.rutaPrevia)
    {
        navigation(startDestination = Destinacio.LlistaGuerrers.rutaBase, route = CategoriaDeNavegacio.CategoriaGuerrers.rutaPrevia){
            composable(route = Destinacio.LlistaGuerrers.rutaGenerica){
                PantallaLlistaGuerrers(
                    llista = Guerrers.dades,
                    onClickGuerrer = {id : Int -> controladorDeNavegacio.navigate(Destinacio.DetallsGuerrer.CreaRutaAmbArguments(id))}
                )
            }
            composable(route = Destinacio.DetallsGuerrer.rutaGenerica, arguments = Destinacio.DetallsGuerrer.navArgs){
                val id = it.arguments?.getInt(ArgumentDeNavegacio.IdGuerrer.clau)
                requireNotNull(id)
                PantallaGuerrer(id = id)
            }
        }

        navigation(startDestination = Destinacio.LlistaOrdinadors.rutaBase, route = CategoriaDeNavegacio.CategoriaOrdinadors.rutaPrevia){
            composable(route = Destinacio.LlistaOrdinadors.rutaGenerica){
                PantallaLlistaOrdinadors(
                    llista = Ordinadors.dades,
                    onClickOrdinador = {id : Int -> controladorDeNavegacio.navigate(Destinacio.DetallsOrdinador.CreaRutaAmbArguments(id))}
                )
            }
            composable(route = Destinacio.DetallsOrdinador.rutaGenerica, arguments = Destinacio.DetallsOrdinador.navArgs){
                val id = it.arguments?.getInt(ArgumentDeNavegacio.IdOrdinador.clau)
                requireNotNull(id)
                PantallaOrdinador(id = id)
            }
        }

        navigation(startDestination = Destinacio.LlistaAliens.rutaBase, route = CategoriaDeNavegacio.CategoriaAliens.rutaPrevia){
            composable(route = Destinacio.LlistaAliens.rutaGenerica){
                PantallaLlistaAliens(
                    llista = Aliens.dades,
                    onClickAlien = {id : Int -> controladorDeNavegacio.navigate(Destinacio.DetallsAlien.CreaRutaAmbArguments(id))}
                )
            }
            composable(route = Destinacio.DetallsAlien.rutaGenerica, arguments = Destinacio.DetallsAlien.navArgs){
                val id = it.arguments?.getInt(ArgumentDeNavegacio.IdAlien.clau)
                requireNotNull(id)
                PantallaAlien(id = id)
            }
        }
    }
}
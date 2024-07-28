package com.app.travelmate.data.repository

import com.app.travelmate.R
import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.data.model.destination.DestinationCatalog
import com.app.travelmate.data.model.destination.DestinationItem
import com.app.travelmate.domain.repository.DestinationRepository

class DestinationRepositoryImpl : DestinationRepository {


    override suspend fun getDestinationList(): DestinationCatalog? {
        val travelDestinations = listOf(
            DestinationItem(
                name = "Italy",
                image = R.drawable.italy,
                cityList = listOf(
                    CityItem(
                        "Rome",
                        R.drawable.rome,
                        "Capital city with rich history and architecture."
                    ),
                    CityItem("Venice", R.drawable.venice, "Famous for its canals and gondolas."),
                    CityItem(
                        "Florence",
                        R.drawable.floerence,
                        "Known for its art and Renaissance history."
                    ),
                    CityItem("Milan", R.drawable.milan, "Fashion capital of Italy."),
                    CityItem(
                        "Naples",
                        R.drawable.naples,
                        "Home of the pizza and vibrant street life."
                    ),
                    CityItem(
                        "Turin",
                        R.drawable.turlin,
                        "Known for its baroque architecture and automotive industry."
                    ),
                    CityItem(
                        "Bologna",
                        R.drawable.bologana,
                        "Famous for its culinary traditions and medieval towers."
                    ),
                    CityItem(
                        "Genoa",
                        R.drawable.genoa,
                        "Important port city with a rich maritime history."
                    ),
                    CityItem(
                        "Palermo",
                        R.drawable.palremo,
                        "Known for its Norman architecture and vibrant markets."
                    ),
                    CityItem(
                        "Genoa",
                        R.drawable.genoa,
                        "Important port city with a rich maritime history."
                    ),
                    CityItem(
                        "Palermo",
                        R.drawable.palremo,
                        "Known for its Norman architecture and vibrant markets."
                    )
                )
            ),
            DestinationItem(
                name = "Japan",
                image = R.drawable.japan,
                cityList = listOf(
                    CityItem(
                        "Tokyo",
                        R.drawable.tokyo,
                        "Bustling capital city with modern and traditional attractions."
                    ),
                    CityItem(
                        "Kyoto",
                        R.drawable.kyoto,
                        "Famous for its classical Buddhist temples."
                    ),
                    CityItem(
                        "Osaka",
                        R.drawable.osaka,
                        "Known for modern architecture and nightlife."
                    ),
                    CityItem(
                        "Hiroshima",
                        R.drawable.hiroshima,
                        "Historic city with a famous peace memorial."
                    ),
                    CityItem(
                        "Nara",
                        R.drawable.nara,
                        "Ancient city with famous temples and deer park."
                    )
                )
            ),
            DestinationItem(
                name = "India",
                image = R.drawable.india,
                cityList = listOf(
                    CityItem(
                        "Delhi",
                        R.drawable.delhi,
                        "Capital city with a rich history and bustling markets."
                    ),
                    CityItem("Mumbai", R.drawable.mumbai, "Financial capital known for Bollywood."),
                    CityItem(
                        "Bangalore",
                        R.drawable.bangalore,
                        "Known as the Silicon Valley of India."
                    ),
                    CityItem(
                        "Kolkata",
                        R.drawable.kolkata,
                        "Cultural capital known for its literature and arts."
                    ),
                    CityItem(
                        "Chennai",
                        R.drawable.chennai,
                        "Known for its classical music and beaches."
                    ),
                    CityItem(
                        "Hyderabad",
                        R.drawable.hyderabad,
                        "Known for its historical monuments and IT industry."
                    ),
                    CityItem(
                        "Pune",
                        R.drawable.pune,
                        "Known for its educational institutions and pleasant climate."
                    )
                )
            ),
            DestinationItem(
                name = "USA",
                image = R.drawable.usa,
                cityList = listOf(
                    CityItem(
                        "New York City",
                        R.drawable.newyork,
                        "The Big Apple, known for its skyline and culture."
                    ),
                    CityItem(
                        "Los Angeles",
                        R.drawable.losangeles,
                        "Home of Hollywood and sunny beaches."
                    ),
                    CityItem("Chicago", R.drawable.chicago, "Known for its architectural marvels."),
                    CityItem(
                        "San Francisco",
                        R.drawable.sanfrancisco,
                        "Famous for the Golden Gate Bridge and tech innovation."
                    ),
                    CityItem(
                        "Las Vegas",
                        R.drawable.lasvegas,
                        "Entertainment capital with vibrant nightlife."
                    )
                )
            ),
            DestinationItem(
                name = "France",
                image = R.drawable.france,
                cityList = listOf(
                    CityItem(
                        "Paris",
                        R.drawable.paris,
                        "The city of love and iconic Eiffel Tower."
                    ),
                    CityItem("Nice", R.drawable.nice, "Beautiful city on the French Riviera."),
                    CityItem(
                        "Lyon",
                        R.drawable.lyon,
                        "Known for its cuisine and historical architecture."
                    ),
                    CityItem(
                        "Marseille",
                        R.drawable.marseille,
                        "Major port city with a rich maritime history."
                    ),
                    CityItem(
                        "Bordeaux",
                        R.drawable.bordeaux,
                        "Famous wine region with stunning vineyards."
                    )
                )
            ),
            DestinationItem(
                name = "Australia",
                image = R.drawable.austrilia,
                cityList = listOf(
                    CityItem(
                        "Sydney",
                        R.drawable.sydney,
                        "Iconic Opera House and beautiful harbor."
                    ),
                    CityItem(
                        "Melbourne",
                        R.drawable.melbourne,
                        "Known for its coffee culture and arts scene."
                    ),
                    CityItem("Brisbane", R.drawable.brisbane, "Vibrant city with a youthful vibe."),
                    CityItem("Perth", R.drawable.perth, "Laid-back city with stunning beaches."),
                    CityItem(
                        "Adelaide",
                        R.drawable.adelaide,
                        "Known for its festivals and wine regions."
                    )
                )
            ),
            DestinationItem(
                name = "Brazil",
                image = R.drawable.brazil,
                cityList = listOf(
                    CityItem(
                        "Rio de Janeiro",
                        R.drawable.riodejanrio,
                        "Famous for its Carnival and Christ the Redeemer statue."
                    ),
                    CityItem(
                        "São Paulo",
                        R.drawable.saupaulo,
                        "Largest city in Brazil with a bustling economy."
                    ),
                    CityItem(
                        "Salvador",
                        R.drawable.salvador,
                        "Known for its Afro-Brazilian culture."
                    ),
                    CityItem(
                        "Brasília",
                        R.drawable.brasilia,
                        "Modernist capital city designed by Oscar Niemeyer."
                    ),
                    CityItem(
                        "Fortaleza",
                        R.drawable.fortaleza,
                        "Popular beach destination with a vibrant nightlife."
                    )
                )
            ),
            DestinationItem(
                name = "Spain",
                image = R.drawable.spain,
                cityList = listOf(
                    CityItem(
                        "Barcelona",
                        R.drawable.barcelona,
                        "Famous for Gaudí's architecture and beaches."
                    ),
                    CityItem(
                        "Madrid",
                        R.drawable.madrid,
                        "Capital city known for its Royal Palace and museums."
                    ),
                    CityItem(
                        "Seville",
                        R.drawable.seville,
                        "Known for its flamenco dancing and historic sites."
                    ),
                    CityItem(
                        "Valencia",
                        R.drawable.valencia,
                        "Famous for its futuristic buildings and paella."
                    ),
                    CityItem(
                        "Granada",
                        R.drawable.granada,
                        "Known for the Alhambra and rich history."
                    )
                )
            )
        )

        return DestinationCatalog(travelDestinations)
    }
}
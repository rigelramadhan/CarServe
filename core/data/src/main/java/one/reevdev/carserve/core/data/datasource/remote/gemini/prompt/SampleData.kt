package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

import one.reevdev.carserve.core.data.datasource.model.service.Finding
import one.reevdev.carserve.core.data.datasource.model.service.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleData

object SampleData {

    val serviceResult = ServiceAnalysisResult(
        vehicle = VehicleData("Car Name 1", "Color 1", "Transmission"),
        recommendedAction = "Recommended action 1",
        findings = listOf(
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
        )
    )

    val servicesList = """
        {
          "services": [
            {
              "name": "Oil Change",
              "price_range": "150000 - 400000"
            },
            {
              "name": "Tire Rotation",
              "price_range": "50000 - 150000"
            },
            {
              "name": "Brake Pad Replacement",
              "price_range": "500000 - 1500000 per axle"
            },
            {
              "name": "Battery Replacement",
              "price_range": "600000 - 1500000"
            },
            {
              "name": "Wheel Alignment",
              "price_range": "200000 - 400000"
            },
            {
              "name": "Air Filter Replacement",
              "price_range": "50000 - 150000"
            },
            {
              "name": "Coolant Flush",
              "price_range": "300000 - 600000"
            },
            {
              "name": "Transmission Fluid Change",
              "price_range": "500000 - 1500000"
            },
            {
              "name": "Spark Plug Replacement",
              "price_range": "200000 - 500000"
            },
            {
              "name": "Fuel System Cleaning",
              "price_range": "250000 - 500000"
            },
            {
              "name": "Timing Belt Replacement",
              "price_range": "1000000 - 3500000"
            },
            {
              "name": "Water Pump Replacement",
              "price_range": "800000 - 2000000"
            },
            {
              "name": "Engine Tune-Up",
              "price_range": "500000 - 2000000"
            },
            {
              "name": "AC Recharge",
              "price_range": "300000 - 600000"
            },
            {
              "name": "Radiator Replacement",
              "price_range": "1000000 - 3000000"
            }
          ]
        }
    """.trimIndent()
}
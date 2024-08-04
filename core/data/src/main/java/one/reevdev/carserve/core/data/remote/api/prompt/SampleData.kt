package one.reevdev.carserve.core.data.remote.api.prompt

import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import one.reevdev.carserve.core.data.feature.service.datasource.model.Finding
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity

object SampleData {

    val serviceResult = ServiceAnalysisResult(
        vehicle = CustomerVehicleEntity(
            policeNo = "AG 2446 NB",
            ownerEmail = "john@doe.com",
            carBrand = "Brand 1",
            carName = "Car Name 1",
            color = "Color 1",
            carType = "Car Type 1",
            transmission = "Transmission"
        ),
        profile = CustomerEntity(
            "John Doe",
            "john@doe.com",
            "081122114114",
            "Jl. Address, West Java, Indonesia"
        ),
        recommendedAction = "Recommended action 1",
        findings = listOf(
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0,
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
        ),
        analysisHtml = """
            <html>
            test
            </html>
        """.trimIndent(),
        createDate = "24/08/2023 - 14:00"
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
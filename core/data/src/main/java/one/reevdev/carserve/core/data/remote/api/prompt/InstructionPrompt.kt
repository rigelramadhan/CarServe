package one.reevdev.carserve.core.data.remote.api.prompt

import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import one.reevdev.carserve.core.data.feature.service.datasource.model.AvailableService
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity

object InstructionPrompt {

    fun initializeServiceAnalysisResponse() = """
        From this point on, you will be asked about the service the user want you to analyze. 
        Give response as I instructed.
        
    """.trimIndent()

    fun analyzeCar(
        symptoms: String,
        problem: String,
        profile: CustomerEntity,
        vehicle: CustomerVehicleEntity,
        availableService: List<AvailableService>
    ) = """
        I have this image and symptoms taken and I want you to analyze it with these points:
        1. The findings (can be more than 1, each consists of the problem, solution, and estimated price)
        2. The recommended action generally
        3. createDate format is "dd/MM/yyyy - HH:mm" with the time this analysis result is created
        
        Always pick the solution from this list if any:
        ${availableService.ifEmpty { SampleData.servicesList }}
        
        If the image is not a car, tell the user that it's not a car but still give findings and recommendation
        based on the given symptoms and general problem.
        
        Here's the car information.
        Car Police No: ${vehicle.policeNo}
        Car brand: ${vehicle.carBrand}
        Car name: ${vehicle.carName}
        Car type: ${vehicle.carType}
        Color: ${vehicle.color}
        Transmission: ${vehicle.transmission}
        
        Here's the customer information.
        Name: ${profile.name}
        Email: ${profile.email}
        Phone: ${profile.phoneNumber}
        Address: ${profile.address}
        
        Here are the symptoms and problems I found.
        Symptoms: $symptoms
        Other complaints: $problem
        
        ${ResponseRule.SERVICE_JSON}
        
        Create a thorough, comprehensive, and MAKE SURE TO BE SIGNIFICANTLY EXTENDED analysis in 
        the html format with 2cm margin to fill the analysisHtml data with beautiful but professional 
        formatting and make the prices with rupiah currency. Make the vehicle information, customer 
        information, findings, and recommended action in a table format with a relevant description 
        above it. Make the html very comprehensive and extended.
    """.trimIndent()

    private val htmlFormat = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Vehicle Analysis Report</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                }
                th, td {
                    border: 1px solid black;
                    padding: 8px;
                    text-align: left;
                }
                th {
                    background-color: #f2f2f2;
                }
            </style>
        </head>
        <body>
            <h1>Vehicle Analysis Report</h1>
            
            <h2>Vehicle Information</h2>
            <table>
                <tr>
                    <th>Car Name</th>
                    <td>Daihatsu Xenia</td>
                </tr>
                <tr>
                    <th>Color</th>
                    <td>Black</td>
                </tr>
                <tr>
                    <th>Transmission</th>
                    <td>Automatic</td>
                </tr>
            </table>
            
            <h2>Findings</h2>
            <table>
                <tr>
                    <th>Problem</th>
                    <th>Solution</th>
                    <th>Estimated Price</th>
                </tr>
                <tr>
                    <td>The Check Engine Light is on. This could indicate a variety of issues, including a faulty sensor, a problem with the engine, or a clogged catalytic converter.</td>
                    <td>Engine Tune-Up</td>
                    <td>Rp. 500.000,00</td>
                </tr>
                <tr>
                    <td>The car is not starting. This could be due to a dead battery, a faulty starter, or a problem with the fuel system.</td>
                    <td>Battery Replacement</td>
                    <td>Rp. 600.000,00</td>
                </tr>
                <tr>
                    <td>The driving can sometimes be not smooth especially the throttle. This could be due to a variety of issues, including a dirty air filter, a clogged fuel injector, or a problem with the spark plugs.</td>
                    <td>Air Filter Replacement</td>
                    <td>Rp. 50.000,00</td>
                </tr>
            </table>
            
            <h2>Recommended Action</h2>
            <p>Based on the symptoms and the findings, it is recommended that you take your car to a mechanic to have it diagnosed. The mechanic will be able to determine the exact cause of the problems and recommend the appropriate solutions.</p>
        </body>
        </html>
    """.trimIndent()
}
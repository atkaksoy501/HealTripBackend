package codewizards.heal_trip.core.utilities.exceptions.problemDetails;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(){
        setTitle("Business Rule Violation");
        setType("http://healtrip.azurewebsites.net/exceptions/business");
        setStatus("400");
    }
}

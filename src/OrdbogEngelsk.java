import java.util.HashMap;
import java.util.Map;

public class OrdbogEngelsk implements IOrdbog
{
    private static Map<String, String> fraser = new HashMap<>();

    @Override
    public String hentFrase(String nøgle)
    {
        return fraser.get(nøgle);
    }

    public OrdbogEngelsk()
    {
        fraser = Tools.indlæsOrdbog("fraserPåEngelsk.csv");
    }
}

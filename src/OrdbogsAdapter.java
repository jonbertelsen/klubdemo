public class OrdbogsAdapter implements IOrdbog
{
    IOrdbog ordbog;

    public OrdbogsAdapter()
    {
        this.ordbog = new OrdbogDansk();
    }

    public void skiftOrdbog()
    {
            int sprog = Tools.inputTal(ordbog.hentFrase("sprogvalg"));
            switch (sprog)
            {
                case 1:
                    ordbog = new OrdbogDansk();
                    break;
                case 2:
                    ordbog = new OrdbogEngelsk();
                    break;
                default:
                    System.out.println("Det sprog findes ikke");
            }
    }

    @Override
    public String hentFrase(String nøgle)
    {
        return ordbog.hentFrase(nøgle);
    }
}

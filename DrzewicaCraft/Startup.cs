using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(DrzewicaCraft.Startup))]
namespace DrzewicaCraft
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}

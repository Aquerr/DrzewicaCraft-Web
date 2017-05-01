using Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrzewicaCraft.Database
{
    public class DataCollector
    {
        //Tymczasowa lista zdjęć
        private static readonly List<Photo> TempPhotos = new List<Photo>()
        {
            new Photo() { Id = 1, Name = "Szkoła podstawowa", Likes = 4 },
            new Photo() { Id = 2, Name = "Kościół", Likes = 10 },
            new Photo() { Id = 3, Name = "Park", Likes = 20 },
            new Photo() { Id = 4, Name = "Jezioro", Likes = 40 }
            //TEST TEAMCITY BUILD
        };

        public Photo GetPhoto(int id)
        {
            var photo = TempPhotos.Single(x => x.Id == id);
            return photo;
        }

        public IEnumerable<Photo> GetPhotos()
        {
            return TempPhotos;
        }
    }
}

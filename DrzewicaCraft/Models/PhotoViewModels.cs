using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DrzewicaCraft.Models
{
    public class PhotoViewModels
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Likes { get; set; }
        public List<Comment> Comments { get; set; }
        public DateTime DateTime { get; set; }
        public Uri ImagePath { get; set; }
    }

    public class Comment
    {
        public int Id { get; set; }
        public ApplicationUser User { get; set; }
        public string Text { get; set; }
    }
}
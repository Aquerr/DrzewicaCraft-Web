using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DrzewicaCraft.Models
{
    public class NewsViewModels
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public DateTime DateTime { get; set; }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using DrzewicaCraft.Models;
using DrzewicaCraft.Database;

namespace DrzewicaCraft.Controllers
{
    public class PhotosController : Controller
    {
        private readonly DataCollector _dataCollector;

        public PhotosController()
        {
            _dataCollector = new DataCollector();
        }

        // GET: Photo
        public ActionResult Index()
        {
            var model = _dataCollector.GetPhotos();

            return View(model);
        }

        // GET: Photo/Detials
        public ActionResult Details(int id)
        {
            var model = _dataCollector.GetPhoto(id);

            return View(model);
        }
    }
}
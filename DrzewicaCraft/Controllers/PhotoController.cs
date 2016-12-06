using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using DataBase;
using DrzewicaCraft.Models;
using Entities;

namespace DrzewicaCraft.Controllers
{
    public class PhotoController : Controller
    {
        private readonly DataCollector _dataCollector;

        public PhotoController()
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
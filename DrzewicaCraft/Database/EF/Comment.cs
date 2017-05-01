//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace DrzewicaCraft.Database.EF
{
    using System;
    using System.Collections.Generic;
    
    public partial class Comment
    {
        public int Id { get; set; }
        public string Text { get; set; }
        public string UserKey { get; set; }
        public System.DateTime Date { get; set; }
        public bool IsCommentToNews { get; set; }
        public bool IsCommentToPhoto { get; set; }
        public int PhotoId { get; set; }
        public int NewsId { get; set; }
    
        public virtual News News { get; set; }
        public virtual Photo Photo { get; set; }
        public virtual User User { get; set; }
    }
}

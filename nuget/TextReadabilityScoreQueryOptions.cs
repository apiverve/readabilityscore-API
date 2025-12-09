using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.TextReadabilityScore
{
    /// <summary>
    /// Query options for the Text Readability Score API
    /// </summary>
    public class TextReadabilityScoreQueryOptions
    {
        /// <summary>
        /// The text to calculate the readability score of
        /// </summary>
        [JsonProperty("text")]
        public string Text { get; set; }
    }
}

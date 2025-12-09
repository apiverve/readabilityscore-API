declare module '@apiverve/readabilityscore' {
  export interface readabilityscoreOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface readabilityscoreResponse {
    status: string;
    error: string | null;
    data: TextReadabilityScoreData;
    code?: number;
  }


  interface TextReadabilityScoreData {
      textCounts:      TextCounts;
      readability:     Readability;
      readabilityText: ReadabilityText;
  }
  
  interface Readability {
      fleschReadingEase:         number;
      fleschKincaidGrade:        number;
      gunningFog:                number;
      colemanLiauIndex:          number;
      smogIndex:                 number;
      automatedReadabilityIndex: number;
      daleChallReadabilityScore: number;
  }
  
  interface ReadabilityText {
      fleschReadingEase:         string;
      daleChallReadabilityScore: string;
  }
  
  interface TextCounts {
      syllableCount: number;
      lexiconCount:  number;
      sentenceCount: number;
  }

  export default class readabilityscoreWrapper {
    constructor(options: readabilityscoreOptions);

    execute(callback: (error: any, data: readabilityscoreResponse | null) => void): Promise<readabilityscoreResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: readabilityscoreResponse | null) => void): Promise<readabilityscoreResponse>;
    execute(query?: Record<string, any>): Promise<readabilityscoreResponse>;
  }
}

declare module '@apiverve/readabilityscore' {
  export interface readabilityscoreOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface readabilityscoreResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class readabilityscoreWrapper {
    constructor(options: readabilityscoreOptions);

    execute(callback: (error: any, data: readabilityscoreResponse | null) => void): Promise<readabilityscoreResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: readabilityscoreResponse | null) => void): Promise<readabilityscoreResponse>;
    execute(query?: Record<string, any>): Promise<readabilityscoreResponse>;
  }
}

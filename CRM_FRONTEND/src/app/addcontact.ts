
export interface AddContact {
    id?: number; // Optional property as it might not be available when creating a new contact
    name: string;
    email: string;
    category: string;
    phoneNumber: string;
    dateCreated: Date | null ;
    country: string;
  }
  

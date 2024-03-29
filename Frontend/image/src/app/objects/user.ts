import {Image} from "./image";

export interface User{
  id:number;
  username:string;
  email:string;
  password:string;
  images:Image[];
}

export type CreateUser = Partial<User>;

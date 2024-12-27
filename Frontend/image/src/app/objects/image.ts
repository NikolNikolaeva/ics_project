import {Tag} from "./tag";
import {User} from "./user";

export interface Image{
  id:number;
  height:number;
  service:string;
  uploadTime:Date;
  url:string;
  width:number;
  tags: Tag[];
  user: User;
}

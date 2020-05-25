import { Email } from './email';
import { Location } from './location';

export class Element {
    elementId: string;
    type: string;
    name: string;
    active: boolean;
    createdTimestamp: Date;
    createBy: Email = new Email();
    location: Location = new Location();
    elementAttributes = {};


}

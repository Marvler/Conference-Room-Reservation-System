import { ConferenceRoom } from "./ConferenceRoom";

export class Organization {
    organizationName: string;
    email: string;
    password: string;
    conferenceRoomsList: ConferenceRoom[] = [];

    constructor(organizationName: string, email: string, password: string) {
        this.organizationName = organizationName;
        this.email = email;
        this.password = password;

    }

}
import { ConferenceRoom } from "./ConferenceRoom";

export class Organization {
    organizationName: string;
    organizationEmail: string;
    organizationPassword: string;
    conferenceRoomsList: ConferenceRoom[] = [];

    constructor(organizationName: string, organizationEmail: string, organizationPassword: string) {
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.organizationPassword = organizationPassword;
    }

}
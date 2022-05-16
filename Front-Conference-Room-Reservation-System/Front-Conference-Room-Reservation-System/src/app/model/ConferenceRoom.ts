import { Reservation } from "./Reservation";

export interface ConferenceRoom {
    conferenceRoomIdentifier: string;
    level: number;
    availability: boolean;
    numberOfSeats: number;
    numberOfStandings: number;
    reservations: Reservation[];

}
import { useEffect, useState } from "react";
import { api } from "./api";

const patientTemplate = {
  fullName: "",
  email: "",
  age: "",
  gender: "",
  phone: "",
  address: "",
  bloodGroup: ""
};

export default function App() {
  const [patients, setPatients] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [records, setRecords] = useState([]);
  const [patientForm, setPatientForm] = useState(patientTemplate);
  const [editingId, setEditingId] = useState(null);
  const [selectedPatient, setSelectedPatient] = useState("");
  const [appointmentPatientId, setAppointmentPatientId] = useState("");
  const [recordPatientId, setRecordPatientId] = useState("");
  const [message, setMessage] = useState("");
  const patientById = Object.fromEntries(patients.map((p) => [p.id, p]));

  useEffect(() => {
    loadData();
  }, []);

  async function loadData() {
    try {
      const [p, a, r] = await Promise.all([
        api.getPatients(),
        api.getAppointments(),
        api.getRecords()
      ]);
      setPatients(p);
      setAppointments(a);
      setRecords(r);
    } catch (err) {
      setMessage(err.message);
    }
  }

  function handlePatientChange(e) {
    const { name, value } = e.target;
    setPatientForm((prev) => ({ ...prev, [name]: value }));
  }

  async function submitPatient(e) {
    e.preventDefault();
    const payload = { ...patientForm, age: Number(patientForm.age) };
    try {
      if (editingId) {
        await api.updatePatient(editingId, payload);
        setMessage("Patient updated successfully");
      } else {
        await api.createPatient(payload);
        setMessage("Patient created successfully");
      }
      setPatientForm(patientTemplate);
      setEditingId(null);
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  async function removePatient(id) {
    try {
      await api.deletePatient(id);
      setMessage("Patient deleted");
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  async function createAppointment(e) {
    e.preventDefault();
    if (!appointmentPatientId) {
      setMessage("Select a patient for this appointment");
      return;
    }
    const form = new FormData(e.currentTarget);
    const payload = {
      patientId: appointmentPatientId,
      doctorName: form.get("doctorName"),
      appointmentDate: form.get("appointmentDate"),
      appointmentTime: form.get("appointmentTime"),
      reason: form.get("reason"),
      status: form.get("status")
    };
    try {
      await api.createAppointment(payload);
      setMessage("Appointment created");
      e.currentTarget.reset();
      setAppointmentPatientId("");
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  async function createRecord(e) {
    e.preventDefault();
    if (!recordPatientId) {
      setMessage("Select a patient for this medical record");
      return;
    }
    const form = new FormData(e.currentTarget);
    const payload = {
      patientId: recordPatientId,
      diagnosis: form.get("diagnosis"),
      treatment: form.get("treatment"),
      notes: form.get("notes")
    };
    try {
      await api.createRecord(payload);
      setMessage("Medical record created");
      e.currentTarget.reset();
      setRecordPatientId("");
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  return (
    <div className="container">
      <header className="header">
        <h1>Patient Management System</h1>
        <p>Microservices-based dashboard for patients, appointments, and records.</p>
      </header>

      {message && <div className="message">{message}</div>}

      <section className="card">
        <h2>{editingId ? "Edit Patient" : "Add Patient"}</h2>
        <form className="grid" onSubmit={submitPatient}>
          {Object.keys(patientTemplate).map((key) => (
            <input
              key={key}
              name={key}
              type={key === "age" ? "number" : "text"}
              placeholder={key}
              value={patientForm[key]}
              onChange={handlePatientChange}
              required
            />
          ))}
          <button type="submit">{editingId ? "Update" : "Create"} Patient</button>
        </form>
      </section>

      <section className="card">
        <h2>Patients</h2>
        <select value={selectedPatient} onChange={(e) => setSelectedPatient(e.target.value)}>
          <option value="">Select Patient for quick actions</option>
          {patients.map((p) => (
            <option key={p.id} value={p.id}>
              {p.fullName}
            </option>
          ))}
        </select>
        <ul className="list">
          {patients.map((p) => (
            <li key={p.id}>
              <div>
                <strong>{p.fullName}</strong> ({p.age}) - {p.bloodGroup}
              </div>
              <div className="actions">
                <button onClick={() => { setPatientForm({ ...p, age: String(p.age) }); setEditingId(p.id); }}>
                  Edit
                </button>
                <button className="danger" onClick={() => removePatient(p.id)}>
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </section>

      <section className="split">
        <form className="card grid" onSubmit={createAppointment}>
          <h2>New Appointment</h2>
          <select
            name="appointmentPatientId"
            value={appointmentPatientId}
            onChange={(e) => setAppointmentPatientId(e.target.value)}
            required
          >
            <option value="">Select patient for this appointment</option>
            {patients.map((p) => (
              <option key={p.id} value={p.id}>
                {p.fullName} ({p.id})
              </option>
            ))}
          </select>
          <input name="doctorName" placeholder="Doctor name" required />
          <input name="appointmentDate" type="date" required />
          <input name="appointmentTime" type="time" required />
          <input name="reason" placeholder="Reason" required />
          <input name="status" placeholder="Status (SCHEDULED)" required />
          <button type="submit">Create Appointment</button>
        </form>

        <form className="card grid" onSubmit={createRecord}>
          <h2>New Medical Record</h2>
          <select
            name="recordPatientId"
            value={recordPatientId}
            onChange={(e) => setRecordPatientId(e.target.value)}
            required
          >
            <option value="">Select patient for this record</option>
            {patients.map((p) => (
              <option key={p.id} value={p.id}>
                {p.fullName} ({p.id})
              </option>
            ))}
          </select>
          <input name="diagnosis" placeholder="Diagnosis" required />
          <input name="treatment" placeholder="Treatment" required />
          <textarea name="notes" placeholder="Notes" rows="4" required />
          <button type="submit">Create Record</button>
        </form>
      </section>

      <section className="split">
        <div className="card">
          <h2>Appointments</h2>
          <ul className="list">
            {appointments.map((a) => (
              <li key={a.id}>
                <div>
                  <strong>{a.patient?.fullName ?? "Unknown Patient"}</strong> ({a.patientId}) with Dr. {a.doctorName} on{" "}
                  {a.appointmentDate} at {a.appointmentTime}
                </div>
              </li>
            ))}
          </ul>
        </div>

        <div className="card">
          <h2>Medical Records</h2>
          <ul className="list">
            {records.map((r) => (
              <li key={r.id}>
                <div>
                  <strong>{patientById[r.patientId]?.fullName ?? "Unknown Patient"}</strong> ({r.patientId}) -{" "}
                  {r.diagnosis} ({r.treatment})
                </div>
              </li>
            ))}
          </ul>
        </div>
      </section>
    </div>
  );
}

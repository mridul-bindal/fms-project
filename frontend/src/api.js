const patientBaseUrl = "http://localhost:8082/patients";
const appointmentBaseUrl = "http://localhost:8081/appointments";
const recordBaseUrl = "http://localhost:8083/records";

async function request(url, options = {}) {
  const response = await fetch(url, {
    headers: { "Content-Type": "application/json" },
    ...options
  });
  if (!response.ok) {
    const text = await response.text();
    throw new Error(text || `Request failed: ${response.status}`);
  }
  if (response.status === 204) {
    return null;
  }
  return response.json();
}

export const api = {
  getPatients: () => request(patientBaseUrl),
  createPatient: (payload) =>
    request(patientBaseUrl, { method: "POST", body: JSON.stringify(payload) }),
  updatePatient: (id, payload) =>
    request(`${patientBaseUrl}/${id}`, { method: "PUT", body: JSON.stringify(payload) }),
  deletePatient: (id) => request(`${patientBaseUrl}/${id}`, { method: "DELETE" }),
  getAppointments: () => request(appointmentBaseUrl),
  createAppointment: (payload) =>
    request(appointmentBaseUrl, { method: "POST", body: JSON.stringify(payload) }),
  getRecords: () => request(recordBaseUrl),
  createRecord: (payload) =>
    request(recordBaseUrl, { method: "POST", body: JSON.stringify(payload) })
};
